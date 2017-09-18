import random
import pytest

from money import Money


def any_amount():
    return random.randint(1, 10000)


def eur(amount):
    return Money(amount, 'EUR')


def usd(amount):
    return Money(amount, 'USD')


def test_money_should_be_addable_when_same_currency():
    assert eur(2) + eur(3) == eur(5)


def test_money_should_have_a_neutral_element_for_add():
    element = eur(any_amount())
    neutral = eur(0)
    assert neutral + element == element + neutral == element


def test_money_should_be_assosiative_for_add():
    x = eur(any_amount())
    y = eur(any_amount())
    z = eur(any_amount())
    assert (x + y) + z == x + (y + z) == x + y + z


def test_money_should_raise_error_when_currency_mismatch():
    with pytest.raises(TypeError) as exception:
        _ = eur(any_amount()) + usd(any_amount())
    assert str(exception.value) == 'Mismatch currency, expected EUR got USD'
