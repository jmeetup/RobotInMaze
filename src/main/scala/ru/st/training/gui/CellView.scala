package ru.st.training.gui

import ru.st.training.domain.CellState.CellState
import ru.st.training.domain.{CellObserver, CellState}
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color._

class CellView(val canvas: Canvas, val a: Double, val b: Double) extends CellObserver {

  val cellWidth: Double = 20.0

  val cellHeight: Double = 20.0

  override def update(eventType: CellState): Unit = {

    def prepare = {
      val startX = a * cellHeight
      val endX = startX + cellHeight
      val startY = b * cellWidth
      val endY = startY
      canvas.graphicsContext2D.setFill(Black)
      canvas.graphicsContext2D.setStroke(SandyBrown)
      canvas.graphicsContext2D.setLineWidth(1)
    }

    def paintRobot:Unit = {
      val startX = a * cellHeight
      val startY = b * cellWidth
      canvas.graphicsContext2D.setFill(Red)
      canvas.graphicsContext2D.fillOval(startX + 4, startY + 4, cellWidth - 8, cellHeight - 8)
    }

    def allWallBuiltWithExit:Unit = {
      allWallBuilt(prepare _)
      val startX = a * cellHeight
      val startY = b * cellWidth
      canvas.graphicsContext2D.setFill(Green)
      canvas.graphicsContext2D.fillRect(startX + 2, startY + 2, cellWidth - 5, cellHeight - 5)
    }

    def topWallRuin:Unit = {
      prepare
      val startX = a * cellHeight
      val endX = startX + cellHeight
      val startY = b * cellWidth
      val endY = startY
      canvas.graphicsContext2D.setStroke(Black)
      canvas.graphicsContext2D.strokeLine(startX, startY, endX, endY)
    }

    def rightWallRuin:Unit = {
      prepare
      val startX = a * cellHeight
      val endX = startX + cellHeight
      val startY = b * cellWidth
      val endY = startY
      canvas.graphicsContext2D.setStroke(Black)
      canvas.graphicsContext2D.strokeLine(startX + cellHeight, startY, endX, endY + cellWidth) //right
    }

    def bottomWallRuin:Unit = {
      prepare
      val startX = a * cellHeight
      val endX = startX + cellHeight
      val startY = b * cellWidth
      val endY = startY
      canvas.graphicsContext2D.setStroke(Black)
      canvas.graphicsContext2D.strokeLine(startX, startY + cellHeight, endX, endY + cellWidth) //bottom
    }

    def leftWallRuinUnit = {
      prepare
      val startX = a * cellHeight
      val endX = startX + cellHeight
      val startY = b * cellWidth
      val endY = startY
      canvas.graphicsContext2D.setStroke(Black)
      canvas.graphicsContext2D.strokeLine(startX, startY, startX, endY + cellWidth) //left
    }

    eventType match {

      case CellState.AllWallBuilt => {
        allWallBuilt(prepare _)
      }

      case CellState.AllWallBuiltWithRobot => {
        allWallBuilt(prepare _)
        paintRobot
      }

      case CellState.AllWallBuiltWithExit => {
        allWallBuiltWithExit
      }

      case CellState.AllWallBuiltWithExitAndRobot => {
        allWallBuiltWithExit
        paintRobot
      }

      case CellState.TopWallRuin => {
        topWallRuin
      }

      case CellState.TopWallRuinWithRobot => {
        topWallRuin
        paintRobot
      }

      case CellState.RightWallRuin => {
        rightWallRuin
      }

      case CellState.RightWallRuinWithRobot => {
        rightWallRuin
        paintRobot
      }

      case CellState.BottomWallRuin => {
        bottomWallRuin
      }

      case CellState.BottomWallRuinWithRobot => {
        bottomWallRuin
        paintRobot
      }

      case CellState.LeftWallRuin => {
        leftWallRuinUnit
      }
      case CellState.LeftWallRuinWithRobot => {
        leftWallRuinUnit
        paintRobot
      }

    }
  }

  private def allWallBuilt(prepare: () => Unit) = {
    prepare()
    val startX = a * cellHeight
    val endX = startX + cellHeight
    val startY = b * cellWidth
    val endY = startY
    canvas.graphicsContext2D.strokeLine(startX, startY, endX, endY)
    canvas.graphicsContext2D.strokeLine(startX, startY + cellHeight, endX, endY + cellWidth) //bottom
    canvas.graphicsContext2D.strokeLine(startX + cellHeight, startY, endX, endY + cellWidth) //right
    canvas.graphicsContext2D.strokeLine(startX, startY, startX, endY + cellWidth) //left
  }
}
