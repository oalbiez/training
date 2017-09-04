from bundle import optimize_bundles, price_bundles
from utils import sequence


def pricer(cart, bundler):
    return sequence(item=cart, operations=(bundler, optimize_bundles, price_bundles))
