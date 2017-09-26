from pytest import mark
from exchange_rates.yahoo import yahoo_exchange_rates
from currency import USD, EUR


@mark.slowtest
def test_yahoo_exchange_rates_should_be_not_null():
    assert yahoo_exchange_rates()(EUR, USD) > 0
