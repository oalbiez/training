

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
        return Price(price.value * (1.0 - self.__value))

    def __repr__(self):
        return "PercentDiscount(" + str(self.__value) + ")"


class Price(object):

    @property
    def value(self):
        return self.__value

    def __init__(self, value):
        self.__value = value

    def discount(self, discount):
        return discount.compute(self)

    def __eq__(self, other):
        return self.__value == other.value

    def __lt__(self, other):
        return self.__value < other.value

    def __le__(self, other):
        return self.__value <= other.value

    def __gt__(self, other):
        return self.__value > other.value

    def __ge__(self, other):
        return self.__value >= other.value

    def __add__(self, other):
        return Price(self.__value + other.value)

    def __mul__(self, factor):
        return Price(self.__value * factor)

    def __sub__(self, other):
        return Price(self.__value - other.value)

    def __repr__(self):
        return "Price(" + str(self.__value) + ")"
