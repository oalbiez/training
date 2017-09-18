

class Wallet(object):

    def __init__(self, *items):
        self.__content = items

    def amount(self, currency, rate_provider):
        return sum((item.change(currency, rate_provider) for item in self.__content), currency(0))
