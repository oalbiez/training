from collections import namedtuple
from functools import total_ordering


class Currency(namedtuple('Currency', 'code precision')):

    def round_amount(self, amount):
        return round(amount, self.precision)

    def render(self, amount):
        return self.code + " " + str(amount)

    def __call__(self, amount):
        return Amount(self.round_amount(amount), self)


EUR = Currency("EUR", 2)
USD = Currency("USD", 3)
XBT = Currency("XBT", 8)


@total_ordering
class Amount(object):

    @property
    def value(self):
        return self.__value

    @property
    def currency(self):
        return self.__currency

    def __init__(self, value, currency):
        self.__value = value
        self.__currency = currency

    def is_currency(self, currency):
        return self.__currency == currency

    def change(self, new_currency, exchange_rates):
        if self.is_currency(new_currency):
            return self
        return new_currency(self.__value * exchange_rates(self.__currency, new_currency))

    def __eq__(self, other):
        return self.__value == other.value and self.__currency == other.currency

    def __lt__(self, other):
        return self.__value < other.value and self.__currency == other.currency

    def __add__(self, other):
        if self.is_currency(other.currency):
            return self.__currency(self.__value + other.value)
        raise TypeError("Mismatch currency, expected " + self.__currency.code + " got " + other.currency.code)

    def __repr__(self):
        return self.__currency.render(self.__value)
