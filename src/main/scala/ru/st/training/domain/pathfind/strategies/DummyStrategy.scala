package ru.st.training.domain.pathfind.strategies

import ru.st.training.domain.{Coordinates, Maze, Robot}
import ru.st.training.domain.pathfind.PathFindStrategy

class DummyStrategy extends PathFindStrategy{

  private def dummyPathFindAlghoritm(c: Coordinates, r: Robot): Boolean = {
    println("It works!")
    true
  }

  def findPath(c: Coordinates, r: Robot): Unit = {
    println(execute(dummyPathFindAlghoritm, c, r))
  }

}
