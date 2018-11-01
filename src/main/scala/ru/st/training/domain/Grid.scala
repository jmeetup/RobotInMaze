package ru.st.training.domain

import scala.collection.mutable.ListBuffer

class Grid(width: Int, height: Int) {

  private var a = 0
  private var b = 0
  private var tmpcells = new ListBuffer[Cell]()
  for (a <- 1 to width; b <- 1 to height) {
    val coordinates = new Coordinates(a, b)
    val cell = new Cell(coordinates)
    tmpcells += cell
  }
  private val cells: List[Cell] = tmpcells.toList

  def getCells: List[Cell] = {
      cells
  }

}
