import urllib.request
from bs4 import BeautifulSoup


# http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22USDEUR%22)&env=store://datatables.org/alltableswithkeys


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
