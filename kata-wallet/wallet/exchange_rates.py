import re
import time
import json
from urllib.parse import urlencode
from urllib.request import urlopen
from bs4 import BeautifulSoup


def rate(currency_from, currency_to, rate):
    return (currency_from, currency_to, rate)


def fixed_exchange_rates(*definitions):

    def key(currency_from, currency_to):
        return currency_from + "-" + currency_to

    rates = dict((key(currency_from, currency_to), rate) for currency_from, currency_to, rate in definitions)

    def rate(currency_from, currency_to):
        return rates[key(currency_from.code, currency_to.code)]
    return rate


def get(url, params):
    with urlopen(url + urlencode(params)) as reply:
        return reply.read()


def yahoo_exchange_rates(getter=get):

    def extract_result(result):
        return BeautifulSoup(result, 'xml').query.results.rate.Rate.text

    def parse_result(text):
        return float(text)

    def process(currency_from, currency_to):
        query = 'select * from yahoo.finance.xchange where pair in ("{0}{1}")'
        return parse_result(extract_result(getter(
            'http://query.yahooapis.com/v1/public/yql?', {
                'q': query.format(currency_from.code, currency_to.code),
                'env': 'store://datatables.org/alltableswithkeys'
            })))

    return process


def google_exchange_rates(getter=get):

    def extract_result(html):
        return BeautifulSoup(html, 'html.parser').find(id='currency_converter_result').span.text

    def parse_result(text):
        return float(text.strip().split(" ")[0])

    def process(currency_from, currency_to):
        return parse_result(extract_result(getter(
            'https://finance.google.com/finance/converter?', {
                'a': 1,
                'from': currency_from.code,
                'to': currency_to.code})))

    return process


def fixerio_exchange_rates(getter=get):
    def process(currency_from, currency_to):
        def parse(js):
            return float(json.loads(js.decode('utf8'))['rates'][currency_to.code])

        return parse(getter('http://api.fixer.io/latest?', {
            'base': currency_from.code,
            'symbols': currency_to.code}))
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
