package ru.st.training.domain

class Cell(coordinates: Coordinates) {
  var isVisited = false
  val room: MazeRoom = new MazeRoom()

  def getCoordinates: Coordinates = {
    coordinates
  }

  def getRoom: MazeRoom = {
    room
  }
}
