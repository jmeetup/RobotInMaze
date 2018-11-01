package ru.st.training

import ru.st.training.domain.Maze

class MazeTest extends UnitSpec {


  "A Maze" should "construct from width and height " in {
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

  it should "contain grid" in {
    val maze = new Maze(20, 20)
    assert(maze.getGrid != null)
  }

  it should "not contain  grid with empty cells" in {
    val maze = new Maze(20, 20)
    assert(!maze.getGrid.getCells.isEmpty)
  }




}
