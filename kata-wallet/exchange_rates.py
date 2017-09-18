import re
import time
import urllib.request
from bs4 import BeautifulSoup


RateDefinition = re.compile("""^
                               \s*(?P<rate_from>([0-9]*[.])?[0-9]+)
                               \s*(?P<currency_from>[A-Z]{3,4})
                               \s*=
                               \s*(?P<rate_to>([0-9]*[.])?[0-9]+)
                               \s*(?P<currency_to>[A-Z]{3,4})$""", re.VERBOSE)


def fixed_exchange_rates(*definitions):

    def key(currency_from, currency_to):
        return currency_from + "-" + currency_to

    def parse(definition):
        match = RateDefinition.match(definition)
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
    url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22{source}{destination}%22)&env=store://datatables.org/alltableswithkeys"

    def extract_result(result):
        return BeautifulSoup(result, 'xml').query.results.rate.Rate.text

    def parse_result(text):
        return float(text)

    def process(currency_from, currency_to):
        req = urllib.request.Request(url=url.format(source=currency_from.code, destination=currency_to.code))
        with urllib.request.urlopen(req) as f:
            return parse_result(extract_result(f.read()))

    return process


def google_exchange_rates():
    url = "https://finance.google.com/finance/converter?a=1&from={source}&to={destination}"

    def extract_result(html):
        return BeautifulSoup(html, 'html.parser').find(id='currency_converter_result').span.text

    def parse_result(text):
        return float(text.strip().split(" ")[0])

    def process(currency_from, currency_to):
        req = urllib.request.Request(url=url.format(source=currency_from.code, destination=currency_to.code))
        with urllib.request.urlopen(req) as f:
            return parse_result(extract_result(f.read()))

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
