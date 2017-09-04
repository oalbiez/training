

class PercentDiscount(object):

    @staticmethod
    def percent(value):
        return PercentDiscount(float(value) / 100)

    @staticmethod
    def none():
        return PercentDiscount(0)

    def __init__(self, value):
        self.__value = value

    def compute(self, price):
        return Price(price.amount * (1.0 - self.__value))

    def __repr__(self):
        return "PercentDiscount(" + str(self.__value) + ")"


class Price(object):

    @property
    def amount(self):
        return self.__amount

    def __init__(self, amount):
        self.__amount = amount

    def discount(self, discount):
        return discount.compute(self)

    def __eq__(self, other):
        return self.__amount == other.amount

    def __ne__(self, other):
        return self.__amount != other.amount

    def __lt__(self, other):
        return self.__amount < other.amount

    def __le__(self, other):
        return self.__amount <= other.amount

    def __gt__(self, other):
        return self.__amount > other.amount

    def __ge__(self, other):
        return self.__amount >= other.amount

    def __add__(self, other):
        return Price(self.__amount + other.amount)

    def __mul__(self, factor):
        return Price(self.__amount * factor)

    def __sub__(self, other):
        return Price(self.__amount - other.amount)

    def __repr__(self):
        return "Price(" + str(self.__amount) + ")"
