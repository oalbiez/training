import re
import time
from urllib.parse import quote
from urllib.request import urlopen, Request
from bs4 import BeautifulSoup


RATE_DEFINITION = re.compile(r"""^
                               \s*(?P<rate_from>([0-9]*[.])?[0-9]+)
                               \s*(?P<currency_from>[A-Z]{3,4})
                               \s*=
                               \s*(?P<rate_to>([0-9]*[.])?[0-9]+)
                               \s*(?P<currency_to>[A-Z]{3,4})$""", re.VERBOSE)


def fixed_exchange_rates(*definitions):

    def key(currency_from, currency_to):
        return currency_from + "-" + currency_to

    def parse(definition):
        match = RATE_DEFINITION.match(definition)
        if not match:
            raise RuntimeError("Cannot parse definition: " + definition)
        return (
            key(match.group('currency_from'), match.group('currency_to')),
            float(match.group('rate_to')) / float(match.group('rate_from')))

    rates = dict(parse(definition) for definition in definitions)

    def rate(currency_from, currency_to):
        return rates[key(currency_from.code, currency_to.code)]
    return rate


def yahoo_exchange_rates():

    def build_request(currency_from, currency_to):
        select = 'select * from yahoo.finance.xchange where pair in ("{0}{1}")'.format(
            currency_from.code,
            currency_to.code)
        return Request(
            url="http://query.yahooapis.com/v1/public/yql?q=" +
            quote(select) +
            "&env=store://datatables.org/alltableswithkeys")

    def extract_result(result):
        return BeautifulSoup(result, 'xml').query.results.rate.Rate.text

    def parse_result(text):
        return float(text)

    def process(currency_from, currency_to):
        with urlopen(build_request(currency_from, currency_to)) as content:
            return parse_result(extract_result(content.read()))

    return process


def google_exchange_rates():
    url = "https://finance.google.com/finance/converter?a=1&from={source}&to={destination}"

    def extract_result(html):
        return BeautifulSoup(html, 'html.parser').find(id='currency_converter_result').span.text

    def parse_result(text):
        return float(text.strip().split(" ")[0])

    def process(currency_from, currency_to):
        req = Request(url=url.format(source=currency_from.code, destination=currency_to.code))
        with urlopen(req) as content:
            return parse_result(extract_result(content.read()))

    return process


def cached_exchange_rates(inner, duration, time_provider=time.time):
    cached_requests = {}

    def key(currency_from, currency_to):
        return currency_from.code + "-" + currency_to.code

    def process(currency_from, currency_to):
        request_key = key(currency_from, currency_to)
        if request_key in cached_requests:
            timestamp, rate = cached_requests[request_key]
            if timestamp + duration > time_provider():
                return rate
        rate = inner(currency_from, currency_to)
        cached_requests[request_key] = (time_provider(), rate)
        return rate

    return process
