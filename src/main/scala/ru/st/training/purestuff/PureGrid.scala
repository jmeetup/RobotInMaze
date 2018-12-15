package ru.st.training.purestuff

import ru.st.training.domain.{Coordinates, MazeRoom, PureCell}

import scala.annotation.tailrec
import scala.util.Random._

class PureGrid(val width: Int, val height: Int, visited: Set[PureCell] = Set()) {

  val cells = makeCells
/*

**--------->x
* |
* |
* |
* y

 */
  @tailrec
  private def helperX(x: Int, y:  Int, acc: Set[PureCell]):Set[PureCell] = {
    if(x == width) acc
    else {
      val cell = new PureCell(new Coordinates(x, y))
      helperX(x + 1, y, acc ++ Set(cell))
    }
  }

  @tailrec
  private def helperY(x: Int, y:  Int, acc: Set[PureCell]):Set[PureCell] = {
    if(y == height) acc
    else {
      val cell = new PureCell(new Coordinates(x, y))
      helperY(x, y + 1, acc ++ helperX(x, y, acc))
    }
  }

  def makeCells(): Set[PureCell] = {
    helperY(0,0, Set())
  }

  def generateExits(exitsCount: Int):Set[PureCell] = {
    //shuffle(getPerimeterCells).take(exitsCount)
    Set()
  }

  def getTotalCells: Int = {
    cells.size
  }
  def getCellByCoordinatesXandY(x: Int, y: Int): Option[PureCell] = {
    val coordinates = Coordinates(x, y)
    cells.find(cell => cell.getCoordinates == coordinates)
  }
  def getCellByCoordinates(coordinates: Coordinates): Option[PureCell] = {
    cells.find(cell => cell.getCoordinates == coordinates)
  }
  def getInitialCell: PureCell = {
    cells.head
  }

  def isAllCellsAreVisited: Boolean = {
    cells.forall(cell =>cell.isVisited)
  }

  def getUnvisitedNeighbours(currentcell: PureCell): Set[PureCell] = {
    cells.diff(visited).filter(cell =>{
      (cell.getCoordinates.x == currentcell.getCoordinates.x && cell.getCoordinates.y == currentcell.getCoordinates.y + 1) ||
        (cell.getCoordinates.x == currentcell.getCoordinates.x && cell.getCoordinates.y == currentcell.getCoordinates.y - 1) ||
        (cell.getCoordinates.y == currentcell.getCoordinates.y && cell.getCoordinates.x == currentcell.getCoordinates.x - 1) ||
        (cell.getCoordinates.y == currentcell.getCoordinates.y && cell.getCoordinates.x == currentcell.getCoordinates.x + 1)
    })
  }

  def markVisited(cell: PureCell):PureGrid = {
    new PureGrid(width, height, visited ++ Set(cell))
  }

  def getPerimeterCells: Set[PureCell] = {
    cells.filter(cell => {
      cell.getCoordinates.x == 0 || cell.getCoordinates.x == width - 1||
        cell.getCoordinates.y == 0 || cell.getCoordinates.y == height - 1
    })
  }

  override def toString = cells.map(cell => s"\n$cell").toString()
}

//тесты пока не написал ¯\_(ツ)_/¯
object Application extends App {
  val grid = new PureGrid(4, 4)
  println(grid)
}
