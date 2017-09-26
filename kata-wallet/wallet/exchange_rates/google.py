from exchange_rates.utils import get
from bs4 import BeautifulSoup


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
