package ru.st.training.domain

import ru.st.training.domain.CellState.CellState

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Cell(coordinates: Coordinates) {
  private var visited = false
  private val room: MazeRoom = MazeRoom()


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
}
