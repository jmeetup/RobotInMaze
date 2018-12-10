package ru.st.training.domain

class Robot(val coordinates: Coordinates, val maze: Maze) {


  require(maze.getCellByCoordinates(coordinates).isDefined, "must init with coordinates which belong to maze")
  var currentCoordinates: Coordinates = coordinates


  def moveToTheTop: Boolean = {
    true
  }

  def moveToTheBottom: Boolean = {
    true
  }

  def moveToTheRight: Boolean = {
    true
  }

  def moveToTheLeft: Boolean = {
    isItPossibleMoveToTheLeft
  }

  def isItExitFromMaze: Boolean = {
    true
  }


  private def isItPossibleMoveToTheLeft: Boolean = {
    val curCell: Option[Cell] = maze.getCellByCoordinates(currentCoordinates)
    var result: Boolean = false
    if (curCell.isDefined) {
      result = (!curCell.get.getRoom.leftWall.isBuilt) && (curCell.get.getCoordinates.x - 1 > 0)
    }
    if (curCell.isDefined && (curCell.get.getCoordinates.x - 1 > 0)) {
      result = !maze.getCellByCoordinates(
        Coordinates(curCell.get.getCoordinates.x - 1, curCell.get.getCoordinates.y))
        .get.getRoom.rightWall.isBuilt
    }
    result
  }

}
