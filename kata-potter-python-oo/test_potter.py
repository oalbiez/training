import random
from bundle import bundle_series_of
from helper_cart import cart, item, any_label
from potter import pricer
from price import Price, PercentDiscount


def test_one_book():
    assert price_for(item(label='A', quantity=1)) == Price(8)


def test_two_differents_books():
    assert price_for(
        item(label='A', quantity=1),
        item(label='B', quantity=1)) == Price((8 + 8) * 0.95)


def test_two_sames_books():
    assert price_for(item(label='A', quantity=2)) == Price((8 + 8))
    assert price_for(
        item(label='A', quantity=1),
        item(label='A', quantity=1)) == Price(8 + 8)


def test_three_differents_books():
    assert price_for(
        item(label='A', quantity=1),
        item(label='B', quantity=1),
        item(label='C', quantity=1)) == Price((8 + 8 + 8) * 0.9)


def test_four_differents_books():
    assert price_for(
        item(label='A', quantity=1),
        item(label='B', quantity=1),
        item(label='C', quantity=1),
        item(label='D', quantity=1)) == Price((8 + 8 + 8 + 8) * 0.8)


def test_five_differents_books():
    assert price_for(
        item(label='A', quantity=1),
        item(label='B', quantity=1),
        item(label='C', quantity=1),
        item(label='D', quantity=1),
        item(label='E', quantity=1)) == Price((8 + 8 + 8 + 8 + 8) * 0.75)


def test_special_case():
    assert price_for(
        item(label='A', quantity=2),
        item(label='B', quantity=2),
        item(label='C', quantity=2),
        item(label='D', quantity=1),
        item(label='E', quantity=1)) == Price(51.2)


def test_random():
    items = [item(label=any_label(1, 1, chars="ABCDE"), quantity=1) for _ in range(random.randint(10, 400))]
    max_cart_price = Price(8) * len(items)
    min_cart_price = max_cart_price.discount(PercentDiscount.percent(25))
    card_price = price_for(*items)
    print(items)
    print(min_cart_price, card_price, max_cart_price)
    assert min_cart_price <= card_price <= max_cart_price


def price_for(*items):
    return pricer(cart(*items), bundle_series_of(labels=['A', 'B', 'C', 'D', 'E']))
