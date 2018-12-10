package ru.st.training.domain.pathfind

import ru.st.training.domain.{Coordinates, Robot}

trait PathFindStrategy {

  def execute[T <: PathFindStrategy]
        (findPathToExit: (Coordinates, Robot) => Boolean, c: Coordinates,  r: Robot) = findPathToExit(c, r)

}