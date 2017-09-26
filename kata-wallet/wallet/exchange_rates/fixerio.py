import json
from exchange_rates.utils import get


def fixerio_exchange_rates(getter=get):
    def process(currency_from, currency_to):
        def parse(content):
            return float(json.loads(content.decode('utf8'))['rates'][currency_to.code])

        return parse(getter('http://api.fixer.io/latest?', {
            'base': currency_from.code,
            'symbols': currency_to.code}))
    return process
