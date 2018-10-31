package ru.st.training

class CellTest  extends UnitSpec {

  "A Cell" should "construct from column number, row number and maze room and not visited yet" in {
    val room = new MazeRoom()
    val cell = new Cell(2, 2, room)
    assert(cell != null)
    assert(!cell.isVisited)
    assert(cell.getRoom == room)
  }

  it should "possible to change room walls" in {
    val room = new MazeRoom()
    val cell = new Cell(2, 2, room)
    assert(cell.getRoom.bottomWall.isBuilt)
    cell.getRoom.bottomWall.ruin
    assert(cell.getRoom == room)
    assert(!cell.getRoom.bottomWall.isBuilt)
    assert(cell.getRoom.topWall.isBuilt)
  }

}
