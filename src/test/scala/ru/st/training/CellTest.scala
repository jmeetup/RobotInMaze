package ru.st.training

import ru.st.training.domain.CellState.CellState
import ru.st.training.domain.{Cell, CellObserver, CellState, Coordinates}

class CellTest extends UnitSpec {

  "A Cell" should "construct from column number, row number and not visited yet" in {

    val cell = new Cell(new Coordinates(2, 2))
    assert(cell != null)
    assert(!cell.isVisited)
  }

  it should "possible to change room walls" in {
    val cell = new Cell(new Coordinates(2, 2))
    assert(cell.getRoom.bottomWall.isBuilt)
    cell.ruinBottomWall
    assert(!cell.getRoom.bottomWall.isBuilt)
    assert(cell.getRoom.topWall.isBuilt)
  }

  it should "possible to mark cell as visited" in {
    val cell = new Cell(new Coordinates(2, 2))
    cell.markVisited
    assert(cell.isVisited)
  }

  it should "notify when room wall ruin" in {
    class O extends CellObserver {

      var isAllWallBuilt: Boolean = false
      var isTopWallRuin: Boolean = false
      var isRightWallRuin: Boolean = false
      var isBottomWallRuin: Boolean = false
      var isLeftWallRuin: Boolean = false

      override def update(eventType: CellState): Unit = {
        eventType match {

          case CellState.AllWallBuilt => {
            isAllWallBuilt = true
          }
          case CellState.TopWallRuin => {
            isTopWallRuin = true
          }

          case CellState.RightWallRuin => {
            isRightWallRuin = true
          }

          case CellState.BottomWallRuin => {
            isBottomWallRuin = true
          }

          case CellState.LeftWallRuin => {
            isLeftWallRuin = true
          }
        }
      }
    }

    val cell = new Cell(new Coordinates(1, 1))
    val o: O = new O
    cell.addObserver(o)
    cell.currentState
    assert(o.isAllWallBuilt)
    cell.ruinTopWall
    assert(o.isTopWallRuin)
    cell.ruinRightWall
    assert(o.isRightWallRuin)
    cell.ruinBottomWall
    assert(o.isBottomWallRuin)
    cell.ruinLeftWall
    assert(o.isLeftWallRuin)

  }

}
