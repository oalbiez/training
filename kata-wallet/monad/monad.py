from urllib.parse import urlencode
from urllib.request import urlopen
from bs4 import BeautifulSoup


class Result(object):

    def apply(self, function):
        raise NotImplemented


class Error(Result):

    @property
    def message(self):
        return self.__message

    @property
    def exception(self):
        return self.__exception

    def __init__(self, message, exception=None):
        self.__message = message
        self.__exception = exception

    def apply(self, function):
        return self

    def __repr__(self):
        return 'Error({})'.format(repr(self.__message))


class Value(Result):

    @staticmethod
    def function(functor):
        def do(*args, **kwargs):
            return Value(functor(*args, **kwargs))
        return do

    @property
    def value(self):
        return self.__value

    def __init__(self, value):
        self.__value = value

    def apply(self, function):
        return function(self.value)

    def __repr__(self):
        return 'Value({})'.format(repr(self.value))


def compose(*functions):
    def process(value):
        result = Value(value)
        for function in functions:
            result = result.apply(function)
        return result
    return process


def url_for(url, params):
    return Value(url + urlencode(params))


def google_finance_url(currencies):
    currency_from, currency_to = currencies
    return url_for(
        'https://finance.google.com/finance/converter?', {
            'a': 1,
            'from': currency_from.code,
            'to': currency_to.code})


def yahoo_finance_url(currencies):
    currency_from, currency_to = currencies
    query = 'select * from yahoo.finance.xchange where pair in ("{0}{1}")'
    return url_for(
        'http://query.yahooapis.com/v1/public/yql?', {
            'q': query.format(currency_from.code, currency_to.code),
            'env': 'store://datatables.org/alltableswithkeys'
        })


def get(url):
    try:
        with urlopen(url) as reply:
            return Value(reply.read())
    except Exception as error:
        return Error("Unable to get url {}".format(url), exception)


def html_soup(text):
    return Value(BeautifulSoup(text, 'html.parser'))


def xml_soup(text):
    return Value(BeautifulSoup(text, 'xml'))


def find_in_soup(*args, **kwargs):
    def find(soup):
        item = soup.find(*args, **kwargs)
        return Value(item) if item else Error('item not found')
    return find


def span(soup):
    return Value(soup.span) if soup.span else Error('span not found')


def text(soup):
    return Value(soup.text) if soup.text else Error('text not found')


def parse(text):
    first, _ = text.strip().split(" ")
    return Value(first) if first else Error('unable to parse')


google_parse = compose(
    html_soup,
    find_in_soup(id='currency_converter_result'),
    span,
    text,
    parse,
    Value.function(float))


def gf_parse(soup):
    return Value(soup.find(id='currency_converter_result').span.text)


def google_finance_parse(html):
    def soup(html):
        return Value(BeautifulSoup(html, 'html.parser'))

    def extract_result(html):
        return BeautifulSoup(html, 'html.parser').find(id='currency_converter_result').span.text

    def parse_result(text):
        return float(text.strip().split(" ")[0])

    return Value(parse_result(extract_result(html)))


def yahoo_finance_parse(html):

    def extract_result(result):
        return BeautifulSoup(result, 'xml').query.results.rate.Rate.text

    def parse_result(text):
        return float(text)

    return Value(parse_result(extract_result(html)))


def cached():
    pass


google_exchange_rates = compose(google_finance_url, get, google_finance_parse)
yahoo_exchange_rates = compose(yahoo_finance_url, get, yahoo_finance_parse)
