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

  private var exits: Set[Cell] = Set.empty[Cell]
  generateExits

  private def generateExits = {
    val perimeterCells: List[Cell] = getPerimeterCells
    val exitCount = roundUp(perimeterCells.length / 4)
    for (i <- 1 to exitCount) {
      val start = 0
      val end: Int = perimeterCells.length - 1
      val rnd = new scala.util.Random
      val rndResult: Int = start + rnd.nextInt((end - start) + 1)
      exits += perimeterCells(rndResult)
    }
    exits.foreach(cell => cell.markExit)

  }

  def roundUp(d: Double) = math.ceil(d).toInt


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


  def getPerimeterCells: List[Cell] = {
    val topEdge = cells.filter(cell =>
      cell.getCoordinates.getY == 1)
    val rightEdge = cells.filter(cell =>
      cell.getCoordinates.getX == height)
    val bottomEdge = cells.filter(cell =>
      cell.getCoordinates.getY == width)
    val leftEdge = cells.filter(cell =>
      cell.getCoordinates.getX == 1)
    val perimeterCells = topEdge ::: rightEdge ::: bottomEdge ::: leftEdge ::: Nil
    perimeterCells.toSet.toList
  }


  def getExits: List[Cell] = {
      exits.toList
  }
}
