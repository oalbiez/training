from hypothesis import given, assume
from hypothesis.strategies import integers, lists

from wallet import Wallet
from currency import USD, EUR, DZD, KRW, XBT
from exchange_rates import fixed_exchange_rates


def test_empty_wallet_should_have_amount_of_0():
    assert amount_in_euro(Wallet()) == EUR(0)


@given(lists(integers(min_value=1, max_value=100000).map(EUR)))
def test_wallet_should_sum_items_with_same_currency(amounts):
    assume(len(amounts) > 1)
    assert amount_in_euro(Wallet(*amounts)) == sum(amounts, EUR(0))


def test_wallet_should_change_item_to_required_currency():
    assert amount_in_euro(Wallet(USD(10)), fixed_exchange_rates("1 USD = 1.1 EUR")) == EUR(11)


def test_real_conversion():
    assert amount_in_euro(
        Wallet(EUR(10), DZD(50), KRW(200), XBT(0.00002354)),
        fixed_exchange_rates("1 DZD = 0.01 EUR", "1 KRW = 0.001 EUR", "1 XBT = 4000 EUR")) == EUR(10.79)


def amount_in_euro(wallet, exchange_rates=None):
    return wallet.amount(EUR, exchange_rates)
