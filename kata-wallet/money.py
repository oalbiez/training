

class Money(object):

    @property
    def amount(self):
        return self.__amount

    @property
    def currency(self):
        return self.__currency

    def __init__(self, amount, currency):
        self.__amount = amount
        self.__currency = currency

    def __eq__(self, other):
        return self.__amount == other.amount and self.__currency == other.currency

    def __add__(self, other):
        if other.currency == self.__currency:
            return Money(self.__amount + other.amount, self.__currency)
        raise TypeError("Mismatch currency, expected " + self.__currency + " got " + other.currency)

    def __repr__(self):
        return self.__currency + " " + str(self.__amount)
