package ru.st.training

import ru.st.training.domain.{Cell, Coordinates, Maze, Robot}

class RobotTest extends UnitSpec  {
  "A Robot" should "init with Coordinates" in {
    val coordinates = new Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    assert(robot != null)
  }

  it should "init with coordinates which belong to maze" in {
    val coordinates = new Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    val cell : Option[Cell] = maze.getCellByCoordinates(coordinates)
    assert(cell.isDefined)
    val wrongCoordinates = new Coordinates(200, 32)
    assertThrows[IllegalArgumentException] {
      val robot2 = new Robot(wrongCoordinates, maze)
    }
    val cell2 : Option[Cell] = maze.getCellByCoordinates(wrongCoordinates)
    assert(cell2.isEmpty)
  }

  it should "possible move to the top" in {
    val coordinates = new Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    robot.moveToTheTop
  }

  it should "possible move to the bottom" in {

  }

  it should "possible move to the left" in {

  }

  it should "possible move to the right" in {

  }

  it should "check is it exit from maze" in {

  }

}
