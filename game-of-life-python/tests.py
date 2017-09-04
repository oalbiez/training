import unittest
import string
from gof import grid, evolve, CellState, next_cell_state


def lines_of(data):
    return [line for line in data.split('\n') if '|' in line]

def get_grid_generation(data, index):
    return grid([line.split('|')[index+1] for line in lines_of(data)])


class GameOfLifeFunctionalTest(unittest.TestCase):

    def test_evolve_should_process_empty_grid(self):
        self.evolve("""
        |  . . .  |  . . .  |  . . .  |
        |  . . .  |  . . .  |  . . .  |
        |  . . .  |  . . .  |  . . .  |
        """)

    def test_evolve_should_process_block_pattern(self):
        self.evolve("""
        |  . . . .  |  . . . .  |  . . . .  |
        |  . # # .  |  . # # .  |  . # # .  |
        |  . # # .  |  . # # .  |  . # # .  |
        |  . . . .  |  . . . .  |  . . . .  |
        """)

    def test_evolve_should_process_blinker_pattern(self):
        self.evolve("""
        |  . . . . .  |  . . . . .  |  . . . . .  |
        |  . . # . .  |  . . . . .  |  . . # . .  |
        |  . . # . .  |  . # # # .  |  . . # . .  |
        |  . . # . .  |  . . . . .  |  . . # . .  |
        |  . . . . .  |  . . . . .  |  . . . . .  |
        """)

    def evolve(self, data):
        current_generation = get_grid_generation(data, 0)
        for index in xrange(1, self.generation_count(data)-1):
            next_generation = get_grid_generation(data, index)
            self.assertEqual(self.render(evolve(current_generation)), self.render(next_generation))
            current_generation = next_generation

    def generation_count(self, data):
        return max((string.count(line, '|') for line in lines_of(data)))

    def render(self, grid):
        return grid.render()


class GameOfLifeUnitTest(unittest.TestCase):

    def test_living_cell_should_die_when_underpopulation(self):
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.LIVE, 0))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.LIVE, 1))

    def test_living_cell_should_stay_alive_when_2_or_3_living_neighbors(self):
        self.assertEqual(CellState.LIVE, next_cell_state(CellState.LIVE, 2))
        self.assertEqual(CellState.LIVE, next_cell_state(CellState.LIVE, 3))

    def test_living_cell_should_die_when_overpopulation(self):
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.LIVE, 4))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.LIVE, 5))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.LIVE, 6))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.LIVE, 7))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.LIVE, 8))

    def test_dead_cell_should_turn_alive_when_3_living_neighbors(self):
        self.assertEqual(CellState.LIVE, next_cell_state(CellState.DEAD, 3))

    def test_dead_cell_should_stay_dead_else(self):
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.DEAD, 0))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.DEAD, 1))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.DEAD, 2))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.DEAD, 4))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.DEAD, 5))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.DEAD, 6))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.DEAD, 7))
        self.assertEqual(CellState.DEAD, next_cell_state(CellState.DEAD, 8))

    def test_living_neighbors_count_when_in_middle_of_grid(self):
        grid = self.grid_for("""
        |  # # #  |
        |  # . #  |
        |  # # #  |
        """)
        self.assertEqual(8, grid.living_neighbors_count(1, 1))

    def grid_for(self, data):
        return get_grid_generation(data, 0)
