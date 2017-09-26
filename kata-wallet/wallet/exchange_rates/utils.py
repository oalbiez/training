from urllib.parse import urlencode
from urllib.request import urlopen


def get(url, params):
    with urlopen(url + urlencode(params)) as reply:
        return reply.read()
