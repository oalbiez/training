from bundle import optimize_bundles, price_bundles


def pricer(cart, bundler):
    return price_bundles(optimize_bundles(bundler(cart)))
