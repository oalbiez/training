from pytest import mark
from exchange_rates.fixerio import fixerio_exchange_rates
from currency import USD, EUR


@mark.slowtest
def test_fixerio_exchange_rates_should_be_not_null():
    assert fixerio_exchange_rates()(EUR, USD) > 0
