from exchange_rates.utils import get
import json


def fixerio_exchange_rates(getter=get):
    def process(currency_from, currency_to):
        def parse(js):
            return float(json.loads(js.decode('utf8'))['rates'][currency_to.code])

        return parse(getter('http://api.fixer.io/latest?', {
            'base': currency_from.code,
            'symbols': currency_to.code}))
    return process
