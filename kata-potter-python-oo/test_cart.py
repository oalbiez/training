from cart import Cart
from helper_cart import cart, item


def test_empty_cart_is_empty():
    assert cart().items == tuple()


def test_emptycart_is_neutral_element():
    neutral = cart()
    element = cart(item())
    assert Cart.concat(neutral, element) == element
    assert Cart.concat(element, neutral) == element


def test_associativity():
    x = cart(item())
    y = cart(item())
    z = cart(item())
    assert Cart.concat(Cart.concat(x, y), z) == Cart.concat(x, Cart.concat(y, z))


def test_concat_two_carts_with_two_differents_items():
    result_cart = Cart.concat(
        cart(item(reference='x', quantity=1)),
        cart(item(reference='y', quantity=1)))
    assert Cart.create(
        item(reference='x', quantity=1),
        item(reference='y', quantity=1)) == result_cart


def test_concat_two_carts_with_same_items():
    result_cart = Cart.concat(
        cart(item(reference='x', quantity=3)),
        cart(item(reference='x', quantity=2)))
    assert Cart.create(item(reference='x', quantity=5)) == result_cart
