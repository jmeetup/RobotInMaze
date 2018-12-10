package ru.st.training

import ru.st.training.domain.{Cell, Coordinates, Maze, Robot}

class RobotTest extends UnitSpec  {
  "A Robot" should "init with Coordinates" in {
    val coordinates = Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    assert(robot != null)
  }

  it should "init with coordinates which belong to maze" in {
    val coordinates = Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    val cell : Option[Cell] = maze.getCellByCoordinates(coordinates)
    assert(cell.isDefined)
    val wrongCoordinates = Coordinates(200, 32)
    assertThrows[IllegalArgumentException] {
      val robot2 = new Robot(wrongCoordinates, maze)
    }
    val cell2 : Option[Cell] = maze.getCellByCoordinates(wrongCoordinates)
    assert(cell2.isEmpty)
  }

  it should "possible move to the top" in {
    val coordinates = Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    robot.moveToTheTop
  }

  it should "possible move to the bottom" in {
    val coordinates = Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    robot.moveToTheBottom
  }

  it should "not possible move to the left when no space there" in {
    val coordinates = Coordinates(1, 1)
    val maze = new Maze(1, 1)
    maze.getCellByCoordinates(coordinates).get.ruinLeftWall
    val robot = new Robot(coordinates, maze)
    assert (!robot.moveToTheLeft)
  }

  it should "not possible move to the left when one wall build" in {
    val goodCoordinates = Coordinates(2, 1)
    val maze2 = new Maze(2, 2)
    maze2.getCellByCoordinates(goodCoordinates).get.ruinLeftWall
    val robot2 = new Robot(goodCoordinates, maze2)
    assert (!robot2.moveToTheLeft)
  }

  it should "possible move to the left" in {
    val goodCoordinates = Coordinates(2, 1)
    val maze2 = new Maze(2, 2)
    maze2.getCellByCoordinates(goodCoordinates).get.ruinLeftWall
    maze2.getCellByCoordinates(Coordinates(1, 1)).get.ruinRightWall
    val robot2 = new Robot(goodCoordinates, maze2)
    assert (robot2.moveToTheLeft)
  }

  it should "possible move to the right" in {
    val coordinates = Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    robot.moveToTheRight
  }

  it should "check is it exit from maze" in {
    val coordinates = Coordinates(2, 2)
    val maze = new Maze(20, 20)
    val robot = new Robot(coordinates, maze)
    robot.isItExitFromMaze
  }

}
