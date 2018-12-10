package ru.st.training

import ru.st.training.domain.Coordinates

class CoordinatesTest extends UnitSpec  {
  "A Coordinates" should "construct from x and y" in {
    val coordinates = Coordinates(2, 2)
    assert(coordinates != null)
  }

  it should "possible to get x and y" in {
    val coordinates = Coordinates(2, 2)
    assert(coordinates.x == 2)
    assert(coordinates.y == 2)
  }

}
