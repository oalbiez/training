from hypothesis import given, assume
from hypothesis.strategies import integers, lists

from wallet import Wallet
from currency import USD, EUR, DZD, KRW, XBT
from exchange_rates.fixed import fixed_exchange_rates, rate


def euros():
    return integers(min_value=1, max_value=100000).map(EUR)


def test_empty_wallet_should_have_amount_of_0():
    assert amount_in_euro(Wallet()) == EUR(0)


@given(lists(euros()))
def test_wallet_should_sum_items_with_same_currency(amounts):
    assume(len(amounts) > 1)
    assert amount_in_euro(Wallet(*amounts)) == sum(amounts, EUR(0))


def test_wallet_should_change_item_to_required_currency():
    assert amount_in_euro(Wallet(USD(10)), fixed_exchange_rates(rate('USD', 'EUR', 1.1))) == EUR(11)


def test_conversion_of_multiple_currencies():
    assert amount_in_euro(
        Wallet(EUR(10), DZD(50), KRW(200), XBT(0.00002354)),
        fixed_exchange_rates(
            rate('DZD', 'EUR', 0.01),
            rate('KRW', 'EUR', 0.001),
            rate('XBT', 'EUR', 4000))) == EUR(10.79)


def amount_in_euro(wallet, exchange_rates=None):
    return wallet.amount(EUR, exchange_rates)
