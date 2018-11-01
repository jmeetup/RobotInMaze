package ru.st.training

import ru.st.training.domain.{Cell, Coordinates}

class CellTest extends UnitSpec {

  "A Cell" should "construct from column number, row number and not visited yet" in {

    val cell = new Cell(new Coordinates(2,2))
    assert(cell != null)
    assert(!cell.isVisited)
  }

  it should "possible to change room walls" in {
    val cell = new Cell(new Coordinates(2,2))
    assert(cell.getRoom.bottomWall.isBuilt)
    cell.getRoom.bottomWall.ruin
    assert(!cell.getRoom.bottomWall.isBuilt)
    assert(cell.getRoom.topWall.isBuilt)
  }

  it should "possible to mark cell as visited" in {
    val cell = new Cell(new Coordinates(2,2))
    cell.markVisited
    assert(cell.isVisited)
  }


}
