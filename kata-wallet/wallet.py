from money import eur


class Wallet(object):

    def __init__(self, *items):
        self.__content = items

    def amount(self, currency):
        return sum(self.__content, eur(0))
