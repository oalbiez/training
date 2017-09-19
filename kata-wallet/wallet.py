

class Wallet(object):

    @property
    def items(self):
        return self.__items

    def __init__(self, *items):
        self.__items = tuple(items)

    def amount(self, currency, rate_provider):
        return sum((item.change(currency, rate_provider) for item in self.items), currency(0))

    def concat(self, other):
        return Wallet(self.items + other.items)

    def __repr__(self):
        return "Wallet(" + ", ".join(repr(item) for item in self.items) + ")"
