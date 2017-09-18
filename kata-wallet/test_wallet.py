from wallet import Wallet
from currency import USD, EUR
from exchange_rates import fixed_exchange_rates


def amount_in_euro(wallet, exchange_rates=None):
    return wallet.amount(EUR, exchange_rates)


def test_empty_wallet_should_have_amount_of_0():
    assert amount_in_euro(Wallet()) == EUR(0)


def test_empty_wallet_should_sum_items_with_same_currency():
    assert amount_in_euro(Wallet(EUR(5), EUR(10))) == EUR(15)


def test_empty_wallet_should_change_item_to_required_currency():
    assert amount_in_euro(Wallet(USD(10)), fixed_exchange_rates("1 USD = 1.1 EUR")) == EUR(11)
