package ru.st.training.domain

import ru.st.training.domain.CellState.CellState

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class PureCell(coordinates: Coordinates) {
  private var visited = false
  private val room: MazeRoom = MazeRoom()
  private var exit = false

  private var observers: ListBuffer[CellObserver] = mutable.ListBuffer[CellObserver]()
  private var cellState: CellState = CellState.AllWallBuilt

  def addObserver(o: CellObserver) = {
    observers += o
  }

  def removeObserver(o: CellObserver) = {
    observers -= o
  }

  def getCoordinates: Coordinates = {
    coordinates
  }

  def getRoom: MazeRoom = {
    room
  }

  def markVisited: Unit = {
    visited = true
  }

  def isVisited: Boolean = {
    visited
  }


  def markExit: Unit = {
    cellState = CellState.AllWallBuiltWithExit
    notifyObservers
    exit = true
  }

  def isExit: Boolean = {
    exit
  }


  def ruinTopWall: Unit = {
    room.topWall.ruin
    cellState = CellState.TopWallRuin
    notifyObservers
  }

  def ruinRightWall: Unit = {
    room.rightWall.ruin
    cellState = CellState.RightWallRuin
    notifyObservers
  }

  def ruinBottomWall: Unit = {
    room.bottomWall.ruin
    cellState = CellState.BottomWallRuin
    notifyObservers
  }

  def ruinLeftWall: Unit = {
    room.leftWall.ruin
    cellState = CellState.LeftWallRuin
    notifyObservers
  }

  def currentState: Unit = {
    notifyObservers
  }

  private def notifyObservers: Unit = {
    observers.foreach(o => o.update(cellState))
  }



  def putRobotIntoCell: Unit = {
    cellState match {

      case CellState.AllWallBuilt => {
        cellState = CellState.AllWallBuiltWithRobot
      }
      case CellState.AllWallBuiltWithExit => {
        cellState = CellState.AllWallBuiltWithExitAndRobot
      }

      case CellState.TopWallRuin => {
        cellState = CellState.TopWallRuinWithRobot
      }

      case CellState.RightWallRuin => {
        cellState = CellState.RightWallRuinWithRobot
      }

      case CellState.BottomWallRuin => {
        cellState = CellState.BottomWallRuinWithRobot
      }

      case CellState.LeftWallRuin => {
        cellState = CellState.LeftWallRuinWithRobot
      }

    }
    notifyObservers
  }
}
