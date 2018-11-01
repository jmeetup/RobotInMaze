package ru.st.training

import ru.st.training.domain.Grid

class GridTest extends UnitSpec {

  "A Grid" should "construct width and height " in {
    val grid = new Grid(10, 10)
    assert(grid != null)
  }

  it should "contains width * height cells" in {
    val grid = new Grid(10, 10)
    assert(grid.getCells.length == (10 * 10))
  }

  it should "construct cells with different coordinates" in {
    val grid = new Grid(1, 2)
    assert(grid.getCells.exists
      (cell => cell.getCoordinates.getX == 1)
    )
    assert(grid.getCells.exists
    (cell => cell.getCoordinates.getY == 2)
    )
    assert(!grid.getCells.exists
    (cell => cell.getCoordinates.getX == 100)
    )
  }

}
