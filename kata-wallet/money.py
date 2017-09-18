from collections import namedtuple
from functools import total_ordering


class Currency(namedtuple('Currency', 'symbol code precision')):

    def round_amount(self, amount):
        return round(amount, self.precision)

    def render(self, amount):
        return self.code + " " + str(amount)

    def __call__(self, amount):
        return Amount(self.round_amount(amount), self)


EUR = Currency("€", "EUR", 2)
USD = Currency("$", "USD", 3)
XBT = Currency("BTC", "XBT", 8)


@total_ordering
class Amount(object):

    @property
    def amount(self):
        return self.__amount

    @property
    def currency(self):
        return self.__currency

    def __init__(self, amount, currency):
        self.__amount = amount
        self.__currency = currency

    def is_currency(self, currency):
        return self.__currency == currency

    def change(self, new_currency, exchange_rates):
        if self.is_currency(new_currency):
            return self
        return new_currency(self.__amount * exchange_rates(self.__currency, new_currency))

    def __eq__(self, other):
        return self.__amount == other.amount and self.__currency == other.currency

    def __lt__(self, other):
        return self.__amount < other.amount and self.__currency == other.currency

    def __add__(self, other):
        if self.is_currency(other.currency):
            return self.__currency(self.__amount + other.amount)
        raise TypeError("Mismatch currency, expected " + self.__currency.code + " got " + other.currency.code)

    def __repr__(self):
        return self.__currency.render(self.__amount)
