from price import Price, PercentDiscount


def test_price_should_be_addable():
    assert Price(2) + Price(3) == Price(5)


def test_price_should_be_multipliable():
    assert Price(2) * 7 == Price(14)


def test_percent_discount():
    assert Price(10).discount(PercentDiscount.percent(10)) == Price(9)
