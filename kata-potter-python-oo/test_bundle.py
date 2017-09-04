from bundle import Bundle, bundle_series_of, optimize_bundles, price_bundles
from helper_cart import cart, item
from price import Price


def test_bundle_serie_of_with_empty_cart():
    bundler = bundle_series_of(labels=['A', 'B'])
    assert list(bundler(cart())) == []


def test_bundle_serie_of_should_build_max_bundles():
    bundler = bundle_series_of(labels=['A', 'B'])
    result_bundles = list(bundler(cart(item(label='A', quantity=3), item(label='B', quantity=2))))
    assert result_bundles == [Bundle.create('A', 'B')] * 2 + [Bundle.create('A')]


def test_bundle_optimisation():
    optimized_bundles = optimize_bundles([Bundle.create('A', 'B', 'C', 'D', 'E'), Bundle.create('A', 'B', 'C')])
    assert optimized_bundles == [Bundle.create('A', 'B', 'C', 'D'), Bundle.create('A', 'B', 'C', 'E')]


def test_pricing_bundle_one_item():
    assert price_bundles([Bundle.create('A')]) == Price(8)


def test_pricing_bundle_two_items():
    assert price_bundles([Bundle.create('A', 'B')]) == Price(15.2)


def test_pricing_bundle_three_items():
    assert price_bundles([Bundle.create('A', 'B', 'C')]) == Price(21.6)
