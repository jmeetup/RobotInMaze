package ru.st.training.domain

import scala.collection.mutable

/**
  * Created by mr.zoom on 02.04.2016.
  */
class Maze(width: Int, height: Int) {
  require(width  > 0 && height > 0, "the width and height must greater then 0.")

  val grid = new Grid(width, height)

  def getGrid: Grid = {
    grid
  }

  /**
    * Recursive backtracker
    * Recursive backtracker on a hexagonal grid
    * The depth-first search algorithm of maze generation is frequently implemented using backtracking:
    *
    * Make the initial cell the current cell and mark it as visited
    * While there are unvisited cells
    * If the current cell has any neighbours which have not been visited
    *   Choose randomly one of the unvisited neighbours
    *   Push the current cell to the stack
    *   Remove the wall between the current cell and the chosen cell
    *   Make the chosen cell the current cell and mark it as visited
    * Else if stack is not empty
    *   Pop a cell from the stack
    *   Make it the current cell
    *
    * @return
    */
  def generate(): Boolean = {
    var backtrackerStack = List[Cell]()
    var currentCell: Cell = prepare

    while(!grid.isAllCellsAreVisited) {

    }

    true
  }

  private def prepare: Cell = {
    val initCell = grid.getInitialCell
    initCell.markVisited
    initCell
  }

//  private def backtracker(stack :List[Cell]): Cell = {
//
//  }




}
