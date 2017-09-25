import time


class Timer(object):
    @property
    def duration(self):
        return self.__duration

    def __init__(self, duration, time_provider=time.time):
        self.__duration = duration
        self.__time_provider = time_provider
        self.__timer = {}

    def mark(self, event):
        self.__timer[event] = self.__time_provider()

    def is_obsolete(self, event):
        if event not in self.__timer:
            return True
        timestamp = self.__timer[event]
        if timestamp + self.duration >= self.__time_provider():
            return False
        del self.__timer[event]
        return True

    def __repr__(self):
        return 'Timer({})'.format(self.__duration)
