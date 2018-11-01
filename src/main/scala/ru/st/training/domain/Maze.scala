package ru.st.training.domain

/**
  * Created by mr.zoom on 02.04.2016.
  */
class Maze(width: Int, height: Int) {

  val grid = new Grid(width, height)

  def getGrid: Grid = {
    grid
  }

  require(width  > 0 && height > 0, "the width and height must greater then 0.")

}
