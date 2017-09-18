from wallet import Wallet
from money import eur


def amount_in_euro(wallet):
    return wallet.amount('EUR')


def test_empty_wallet_should_have_amount_of_0():
    assert amount_in_euro(Wallet()) == eur(0)


def test_empty_wallet_should_sum_items_with_same_currency():
    assert amount_in_euro(Wallet(eur(5), eur(10))) == eur(15)
