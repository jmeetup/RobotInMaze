package ru.st.training.domain

class Cell(coordinates: Coordinates) {
  private var visited = false
  private val room: MazeRoom = new MazeRoom()

  def getCoordinates: Coordinates = {
    coordinates
  }

  def getRoom: MazeRoom = {
    room
  }

  def markVisited: Unit = {
    visited = true
  }

  def isVisited: Boolean = {
    visited
  }
}
