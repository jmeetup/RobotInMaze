package ru.st.training

/**
  * Created by mr.zoom on 02.04.2016.
  */
class Maze(width: Int, height: Int) {
  val maze = Array.ofDim[Location](width, height)

  def initLocationByCoordinate(x: Int, y: Int, location: Location) = {
      maze(x-1)(y-1) = location
  }
}
