from collections import namedtuple
from itertools import chain
from utils import group_by, unseen


class CartItem(namedtuple('CartItem', 'reference quantity')):
    @staticmethod
    def normalize(items):
        cache = group_by(items, keyfunc=lambda i: i.reference)
        return tuple(CartItem(reference=reference, quantity=sum(item.quantity for item in cache[reference]))
                     for reference in unseen(item.reference for item in items))


class Cart(namedtuple('Cart', 'items')):
    @staticmethod
    def empty():
        return Cart(items=())

    @staticmethod
    def create(*items):
        return Cart(items=CartItem.normalize(items))

    @staticmethod
    def concat(*carts):
        return Cart.create(*chain.from_iterable(cart.items for cart in carts))
