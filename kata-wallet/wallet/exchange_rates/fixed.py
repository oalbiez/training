

def rate(currency_from, currency_to, rate):
    return (currency_from, currency_to, rate)


def fixed_exchange_rates(*definitions):

    def key(currency_from, currency_to):
        return currency_from + "-" + currency_to

    rates = dict((key(currency_from, currency_to), rate) for currency_from, currency_to, rate in definitions)

    def rate(currency_from, currency_to):
        return rates[key(currency_from.code, currency_to.code)]
    return rate
