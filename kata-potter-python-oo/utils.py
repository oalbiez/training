from collections import defaultdict


def group_by(items, keyfunc):
    result = defaultdict(list)
    for item in items:
        result[keyfunc(item)].append(item)
    return result


def unseen(items):
    seen = set()
    seen_add = seen.add
    for item in items:
        if item not in seen:
            seen_add(item)
            yield item
