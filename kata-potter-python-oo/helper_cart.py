import random
import string
from cart import Cart, CartItem


def any_label(min_lenght=3, max_lenght=20):
    return ''.join(random.sample(string.ascii_letters + string.digits, random.randint(min_lenght, max_lenght)))


def any_quantity(min_quantity=1, max_quantity=100):
    return random.randint(min_quantity, max_quantity)


def item(label=None, quantity=None):
    return CartItem(label=label or any_label(), quantity=quantity or any_quantity())


def cart(*items):
    return Cart.create(*items)
