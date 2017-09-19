from exchange_rates import google_exchange_rates, yahoo_exchange_rates
from wallet import Wallet
from currency import USD, EUR


def _test_yahoo_exchange_rates_should_be_not_null():
    assert yahoo_exchange_rates()(EUR, USD) > 0


def _test_google_exchange_rates_should_be_not_null():
    assert google_exchange_rates()(EUR, USD) > 0


def _test_real_conversion():
    assert Wallet(EUR(10), USD(0.01)).amount(EUR, google_exchange_rates()) > EUR(10)
