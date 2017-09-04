import string
from enum import Enum

class CellState(Enum):
    LIVE = 1
    DEAD = 2


class Grid:
    def __init__(self, living_cells, min_x, min_y, max_x, max_y):
        self.living_cells = set(living_cells)
        self.min_x = min_x
        self.min_y = min_y
        self.max_x = max_x
        self.max_y = max_y

    def cell_state(self, x, y):
        if (x, y) in self.living_cells:
            return CellState.LIVE
        return CellState.DEAD

    def living_neighbors_count(self, x, y):
        count = 0
        for dx in xrange(x-1, x+2):
            for dy in xrange(y-1, y+2):
                if not (dx == x and dy == y):
                    if self.cell_state(dx, dy) == CellState.LIVE:
                        count += 1
        return count

    def render(self):
        grid = []
        for x in xrange(self.min_x, self.max_x):
            line = []
            for y in xrange(self.min_y, self.max_y):
                if self.cell_state(x, y) == CellState.LIVE:
                    line.append('#')
                else:
                    line.append('.')
            grid.append(string.join(line, ' '))
        return string.join(grid, '\n')


def grid(lines):
    def size_x():
        return len(lines[0].split())
    def size_y():
        return len(lines)

    living_cells = []
    for y, content in enumerate(lines):
        for x, value in enumerate(content.split()):
            if value != '.':
                living_cells.append((x, y))
    return Grid(living_cells, 0, 0, size_x(), size_y())


def evolve(grid):
    next_living_cells = []
    for x in xrange(grid.min_x, grid.max_x):
        for y in xrange(grid.min_y, grid.max_y):
            if next_cell_state(grid.cell_state(x, y), grid.living_neighbors_count(x, y)) == CellState.LIVE:
                next_living_cells.append((x, y))
    return Grid(next_living_cells, grid.min_x, grid.min_y, grid.max_x, grid.max_y)


def next_cell_state(current, living_neighbors_count):
    if current == CellState.DEAD:
        if living_neighbors_count == 3:
            return CellState.LIVE
        return CellState.DEAD

    if living_neighbors_count in [2, 3]:
        return CellState.LIVE
    return CellState.DEAD
