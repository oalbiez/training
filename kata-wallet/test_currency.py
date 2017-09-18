import random
import pytest

from helper_rates import exchange_rates
from currency import EUR, USD


def any_amount():
    return random.randint(1, 10000)


def test_EUR_should_have_precision_on_two_digits():
    assert EUR.round_amount(1.234) == 1.23


def test_USD_should_have_precision_on_three_digits():
    assert USD.round_amount(1.2347) == 1.235


def test_money_should_be_addable_when_same_currency():
    assert EUR(2) + EUR(3) == EUR(5)


def test_money_should_have_a_neutral_element_for_add():
    element = EUR(any_amount())
    neutral = EUR(0)
    assert neutral + element == element + neutral == element


def test_money_should_be_assosiative_for_add():
    x = EUR(any_amount())
    y = EUR(any_amount())
    z = EUR(any_amount())
    assert (x + y) + z == x + (y + z) == x + y + z


def test_money_should_raise_error_when_currency_mismatch():
    with pytest.raises(TypeError) as exception:
        _ = EUR(any_amount()) + USD(any_amount())
    assert str(exception.value) == 'Mismatch currency, expected EUR got USD'


def test_money_should_be_changed_with_an_exchange_rate():
    assert EUR(10).change(USD, exchange_rates("1 EUR = 1.19 USD")) == USD(11.9)
