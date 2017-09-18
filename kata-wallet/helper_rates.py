import re

RateDefinition = re.compile("^\s*(?P<rate_from>([0-9]*[.])?[0-9]+)\s*(?P<currency_from>[A-Z]{3,4})\s*=\s*(?P<rate_to>([0-9]*[.])?[0-9]+)\s*(?P<currency_to>[A-Z]{3,4})$")

def exchange_rates(*definitions):
    rates = {}

    def key(currency_from, currency_to):
        return currency_from + "-" + currency_to

    def parse(definition):
        match = RateDefinition.match(definition)
        if match:
            rates[key(match.group('currency_from'), match.group('currency_to'))] = float(match.group('rate_to')) / float(match.group('rate_from'))

    for definition in definitions:
        parse(definition)

    def rate(currency_from, currency_to):
        return rates[key(currency_from.code, currency_to.code)]
    return rate



# 1 EUR = 1.19 USD
# 1 USD = 0.84 EUR

# 1 USD = 0,000248 XBT
# 1 XBT = 4032,26 USD
# 4036,82
