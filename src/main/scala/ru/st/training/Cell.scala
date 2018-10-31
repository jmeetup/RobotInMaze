package ru.st.training

class Cell(row: Int, column: Int, room: MazeRoom) {
  var isVisited = false

  def getRoom: MazeRoom = {
    room
  }
}
