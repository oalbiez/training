from utils import Timer

from hypothesis import given, assume
from hypothesis.strategies import integers, text


@given(text(min_size=3, max_size=5))
def test_empty_timer_should_have_obsolete_event(event):
    assert Timer(300).is_obsolete(event)


@given(integers())
def test_when_timer_is_not_obsolete(delay):
    assume(delay <= 10 and delay >= 0)
    assert not EventOfDuration(10).is_obsolete_after(delay)


@given(integers())
def test_when_timer_is_obsolete(delay):
    assume(delay > 10)
    assert EventOfDuration(10).is_obsolete_after(delay)


class EventOfDuration(object):
    def __init__(self, duration):
        self.duration = duration
        self.time = 0

    def __call__(self):
        return self.time

    def advance(self, delay):
        self.time += delay

    def is_obsolete_after(self, delay):
        timer = Timer(self.duration, time_provider=self)
        timer.mark("event")
        self.advance(delay)
        return timer.is_obsolete("event")
