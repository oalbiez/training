from collections import namedtuple
from utils import group_by, unseen


class CartItem(namedtuple('CartItem', 'label quantity')):
    @staticmethod
    def normalize(items):
        cache = group_by(items, keyfunc=lambda i: i.label)
        return tuple(CartItem(label=label, quantity=sum((item.quantity for item in cache[label])))
                     for label in unseen((item.label for item in items)))


class Cart(namedtuple('Cart', 'items')):
    @staticmethod
    def empty():
        return Cart(items=())

    @staticmethod
    def create(*items):
        return Cart(items=CartItem.normalize(items))

    @staticmethod
    def concat(*carts):
        return Cart.create(*[item for cart in carts for item in cart.items])
