from collections import namedtuple


class Currency(namedtuple('Currency', 'symbol code precision')):

    def round_amount(self, amount):
        return round(amount, self.precision)

    def __call__(self, amount):
        return Money(amount, self)


EUR = Currency("€", "EUR", 2)
USD = Currency("$", "USD", 3)
XBT = Currency("BTC", "XBT", 8)


class Money(object):

    @property
    def amount(self):
        return self.__amount

    @property
    def currency(self):
        return self.__currency

    def __init__(self, amount, currency):
        self.__amount = currency.round_amount(amount)
        self.__currency = currency

    def is_currency(self, currency):
        return self.__currency == currency

    def change(self, new_currency, exchange_rates):
        if self.is_currency(new_currency):
            return self
        return Money(self.__amount * exchange_rates(self.__currency, new_currency), new_currency)

    def __eq__(self, other):
        return self.__amount == other.amount and self.__currency == other.currency

    def __add__(self, other):
        if self.is_currency(other.currency):
            return Money(self.__amount + other.amount, self.__currency)
        raise TypeError("Mismatch currency, expected " + self.__currency.code + " got " + other.currency.code)

    def __repr__(self):
        return self.__currency.code + " " + str(self.__amount)
