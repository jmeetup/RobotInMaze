package ru.st.training.domain

class Robot(val coordinates: Coordinates, val maze: Maze) {


  require(maze.getCellByCoordinates(coordinates).isDefined, "must init with coordinates which belong to maze")
  var currentCoordinates: Coordinates = coordinates


  def moveToTheTop: Boolean = ???
}
