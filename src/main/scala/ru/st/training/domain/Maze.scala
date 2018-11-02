package ru.st.training.domain

/**
  * Created by mr.zoom on 02.04.2016.
  */
class Maze(width: Int, height: Int) {
  require(width > 0 && height > 0, "the width and height must greater then 0.")

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
    var backtrackerStack = List.empty[Cell]
    var currentCell: Cell = prepare

    while (!grid.isAllCellsAreVisited) {
      val unvisitedNeighbours = grid.getUnvisitedNeighbours(currentCell)
      if (!unvisitedNeighbours.isEmpty) {
        val start = 0
        val end = unvisitedNeighbours.length - 1
        val rnd = new scala.util.Random
        val rndResult = start + rnd.nextInt((end - start) + 1)
        val chosenCell = unvisitedNeighbours(rndResult)
        backtrackerStack = currentCell :: backtrackerStack
        removeTheWall(currentCell, chosenCell)
        currentCell = chosenCell
        currentCell.markVisited

      }
      else if (!backtrackerStack.isEmpty) {
        val stackCell = backtrackerStack.head
        backtrackerStack = backtrackerStack.tail
        currentCell = stackCell
      }
    }

    true
  }

  private def prepare: Cell = {
    val initCell = grid.getInitialCell
    initCell.markVisited
    initCell
  }

  private def removeTheWall(currentCell: Cell, chosenCell: Cell): Unit = {
    if (currentCell.getCoordinates.getX == chosenCell.getCoordinates.getX) {
      if (currentCell.getCoordinates.getY == chosenCell.getCoordinates.getY - 1) {
        currentCell.ruinBottomWall
        chosenCell.ruinTopWall
      } else if (currentCell.getCoordinates.getY == chosenCell.getCoordinates.getY + 1) {
        currentCell.ruinTopWall
        chosenCell.ruinBottomWall
      }
    } else {
      if (currentCell.getCoordinates.getX == chosenCell.getCoordinates.getX - 1) {
        currentCell.ruinRightWall
        chosenCell.ruinLeftWall
      } else if (currentCell.getCoordinates.getX == chosenCell.getCoordinates.getX + 1) {
        currentCell.ruinLeftWall
        chosenCell.ruinRightWall
      }
    }
  }


  //  private def backtracker(stack :List[Cell]): Cell = {
  //
  //  }
  //


}
