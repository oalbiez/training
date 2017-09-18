from exchange_rates import google_exchange_rates, yahoo_exchange_rates
from wallet import Wallet
from currency import USD, EUR, DZD, KRW



def test_yahoo_exchange_rates_should_be_not_null():
    assert yahoo_exchange_rates()(EUR, USD) > 0


def test_google_exchange_rates_should_be_not_null():
    assert google_exchange_rates()(EUR, USD) > 0


def test_real_conversion():
    assert Wallet(EUR(10), DZD(50), KRW(200)).amount(EUR, google_exchange_rates()) > EUR(10)
