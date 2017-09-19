import random
import pytest

from exchange_rates import fixed_exchange_rates
from currency import EUR, USD


def any_amount():
    return random.randint(1, 10000)


def test_eur_should_have_2_digit_precision():
    assert EUR.round_amount(1.234) == 1.23


def test_usd_should_have_3_digit_precision():
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
    assert EUR(10).change(USD, fixed_exchange_rates("1 EUR = 1.19 USD")) == USD(11.9)
