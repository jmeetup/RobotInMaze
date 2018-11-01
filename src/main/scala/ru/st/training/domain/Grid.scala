package ru.st.training.domain

import scala.collection.mutable.ListBuffer

class Grid(val width: Int, val height: Int) {


  private var a = 0
  private var b = 0
  private var tmpcells = new ListBuffer[Cell]()
  for (a <- 1 to width; b <- 1 to height) {
    val coordinates = new Coordinates(a, b)
    val cell = new Cell(coordinates)
    tmpcells += cell
  }
  private val cells: List[Cell] = tmpcells.toList
  tmpcells = null


  def getTotalCells: Int = {
    cells.length
  }


  def getCellByCoordinatesXandY(x: Int, y: Int): Option[Cell] = {
    val coordinates = new Coordinates(x, y)
    cells.find(cell => cell.getCoordinates == coordinates)
  }

  def getCellByCoordinates(coordinates: Coordinates): Option[Cell] = {
    cells.find(cell => cell.getCoordinates == coordinates)
  }

  def getInitialCell: Cell = {
    cells.head
  }

  def isAllCellsAreVisited: Boolean = {
    val notVisited: List[Cell] = cells.filter(cell => !cell.isVisited)
    notVisited.isEmpty
  }

  def getUnvisitedNeighbours(currentcell: Cell): List[Cell] = {
    val coordinates: Coordinates = currentcell.getCoordinates
    val topNieghbour = cells.find(cell =>
      (cell.getCoordinates.getX == currentcell.getCoordinates.getX)
        && (cell.getCoordinates.getY == currentcell.getCoordinates.getY - 1))
    val rightNieghbour = cells.find(cell =>
      (cell.getCoordinates.getX == currentcell.getCoordinates.getX + 1)
        && (cell.getCoordinates.getY == currentcell.getCoordinates.getY))
    val bottomNieghbour = cells.find(cell =>
      (cell.getCoordinates.getX == currentcell.getCoordinates.getX)
        && (cell.getCoordinates.getY == currentcell.getCoordinates.getY + 1))
    val leftNieghbour = cells.find(cell =>
      (cell.getCoordinates.getX == currentcell.getCoordinates.getX - 1)
        && (cell.getCoordinates.getY == currentcell.getCoordinates.getY))
    val neighbours = List(topNieghbour, rightNieghbour, bottomNieghbour, leftNieghbour)
    neighbours.flatten.filter(neighbour => !neighbour.isVisited)
  }

}
