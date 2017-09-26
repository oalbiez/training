from exchange_rates.utils import get
from bs4 import BeautifulSoup


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
