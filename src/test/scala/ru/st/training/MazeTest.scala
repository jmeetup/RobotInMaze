package ru.st.training

class MazeTest extends UnitSpec {


  "A Maze" should "construct from with and height " in {
        val maze = new Maze(20, 20)
        assert(maze != null)
  }

  it should "produce IllegalArgumentException when width less 0" in {
    assertThrows[IllegalArgumentException] {
      val maze = new Maze(-21, 20)
    }
  }

  it should "produce IllegalArgumentException when height less 0" in {
    assertThrows[IllegalArgumentException] {
      val maze = new Maze(21, -20)
    }
  }





}
