from collections import namedtuple
from itertools import chain
from price import Price, PercentDiscount
from utils import group_by


class Bundle(namedtuple('Bundle', 'size')):
    pass


def bundle_series_of(references):
    def consume_all(quantity_by_reference):
        references = list(quantity_by_reference.keys())
        for reference in references:
            quantity_by_reference[reference] -= 1
            if quantity_by_reference[reference] == 0:
                del quantity_by_reference[reference]
        return len(references)

    def process(cart):
        quantity_by_reference = dict((item.reference, item.quantity) for item in cart.items if item.reference in references)
        while quantity_by_reference:
            yield Bundle(size=consume_all(quantity_by_reference))

    return process


def optimize_bundles(bundles):
    bundle_by_size = group_by(bundles, keyfunc=lambda i: i.size)
    while bundle_by_size[3] and bundle_by_size[5]:
        bundle_by_size[4].extend([Bundle(size=4), Bundle(size=4)])
        bundle_by_size[3].pop()
        bundle_by_size[5].pop()
    return list(chain(*bundle_by_size.values()))


def price_bundle(bundle):
    discount = [
        PercentDiscount.none(),
        PercentDiscount.none(),
        PercentDiscount.percent(5),
        PercentDiscount.percent(10),
        PercentDiscount.percent(20),
        PercentDiscount.percent(25)]
    return (Price(8) * bundle.size).discount(discount[bundle.size])


def price_bundles(bundles):
    return sum((price_bundle(bundle) for bundle in bundles), Price(0))
