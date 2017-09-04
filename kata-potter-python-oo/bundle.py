from collections import namedtuple, defaultdict
from itertools import chain
from price import Price, PercentDiscount
from utils import group_by


class Bundle(namedtuple('Bundle', ['labels'])):
    @staticmethod
    def create(*labels):
        return Bundle(labels=frozenset(labels))


def bundle_series_of(labels):
    def build_quantity_by_label(items):
        quantity_by_label = defaultdict(lambda: 0)
        for item in items:
            quantity_by_label[item.label] += item.quantity
        return quantity_by_label

    def consume_all(quantity_by_label):
        for label in list(quantity_by_label.keys()):
            quantity_by_label[label] -= 1
            if quantity_by_label[label] == 0:
                del quantity_by_label[label]

    def process(cart):
        quantity_by_label = build_quantity_by_label([item for item in cart.items if item.label in labels])
        while quantity_by_label:
            yield Bundle.create(*quantity_by_label.keys())
            consume_all(quantity_by_label)
    return process


def optimize_bundles(bundles):
    def merge(three, five):
        element = set([next(iter(sorted(five.labels ^ three.labels)))])
        return [Bundle.create(*(three.labels | element)), Bundle.create(*(five.labels ^ element))]

    bundle_by_size = group_by(bundles, keyfunc=lambda i: len(i.labels))
    while bundle_by_size[3] and bundle_by_size[5]:
        bundle_by_size[4].extend(merge(bundle_by_size[3].pop(), bundle_by_size[5].pop()))
    return list(chain(*bundle_by_size.values()))


def price_bundle(bundle):
    discount = [
        PercentDiscount.none(),
        PercentDiscount.none(),
        PercentDiscount.percent(5),
        PercentDiscount.percent(10),
        PercentDiscount.percent(20),
        PercentDiscount.percent(25)]
    return (Price(8) * len(bundle.labels)).discount(discount[len(bundle.labels)])


def price_bundles(bundles):
    return sum([price_bundle(bundle) for bundle in bundles], Price(0))
