import time


def cached_exchange_rates(inner, duration, time_provider=time.time):
    cached_requests = {}

    def key(currency_from, currency_to):
        return currency_from.code + "-" + currency_to.code

    def process(currency_from, currency_to):
        request_key = key(currency_from, currency_to)
        if request_key in cached_requests:
            timestamp, rate = cached_requests[request_key]
            if timestamp + duration > time_provider():
                return rate
        rate = inner(currency_from, currency_to)
        cached_requests[request_key] = (time_provider(), rate)
        return rate

    return process
