package ru.st.training

import ru.st.training.domain.{Cell, Coordinates, Grid}

class GridTest extends UnitSpec {

  "A Grid" should "construct width and height " in {
    val grid = new Grid(10, 10)
    assert(grid != null)
  }

  it should "contains width * height cells" in {
    val grid = new Grid(10, 10)
    assert(grid.getTotalCells == (10 * 10))
  }

  it should "construct cells with different coordinates" in {
    val grid = new Grid(1, 2)
    assert(grid.getCellByCoordinatesXandY(1, 2) != null)

    assert(grid.getCellByCoordinates(Coordinates(1, 100)).isEmpty)
  }

  it should "return init cell" in {
    val grid = new Grid(10, 20)
    assert(grid.getInitialCell != null)
    val cell = grid.getInitialCell
    val coordinates = Coordinates(1, 1)
    assert(cell.getCoordinates == coordinates)
  }

  it should "possible to know all cells are visited" in {
    val grid = new Grid(1, 1)
    grid.getInitialCell.markVisited
    assert(grid.isAllCellsAreVisited)
    val hugeGrid = new Grid(20, 20)
    hugeGrid.getInitialCell.markVisited
    assert(!hugeGrid.isAllCellsAreVisited)
  }

  it should "possible get unvisited neighbours" in {
    val grid = new Grid(2, 2)
    grid.getInitialCell.markVisited
    val unvisitedNeighbours: List[Cell] = grid.getUnvisitedNeighbours(grid.getInitialCell)
    assert(unvisitedNeighbours.length == 2)
    assert(unvisitedNeighbours.exists(neighb =>
      neighb.getCoordinates.x == 1 && neighb.getCoordinates.y == 2))
    val someCell = grid.getCellByCoordinatesXandY(1, 2)
    if (someCell.isDefined) someCell.get.markVisited
    val secondUnvisitedNeighbours: List[Cell] = grid.getUnvisitedNeighbours(grid.getInitialCell)
    assert(!secondUnvisitedNeighbours.exists(neighb =>
      neighb.getCoordinates.x == 1 && neighb.getCoordinates.y == 2))
  }

  it should "possible get perimiter cells" in {
    val grid = new Grid(3, 3)
    val perimiter: List[Cell] = grid.getPerimeterCells
    assert(perimiter.exists(cell =>
      cell.getCoordinates == Coordinates(1, 1)
    ))
    assert(!perimiter.exists(cell =>
      cell.getCoordinates == Coordinates(2, 2)
    ))
  }


  it should "possible get some exits on grid" in {
    val grid = new Grid(3, 3)
    val  exits: List[Cell] = grid.getExits
    assert(exits.nonEmpty)
    exits.foreach(exitCell => assert(exitCell.isExit))
  }
}
