package ru.st.training.gui

import ru.st.training.domain.CellState.CellState
import ru.st.training.domain.{CellObserver, CellState}
import ru.st.training.gui.ScalaFXHelloWorld.{canvasHeight, canvasWidth}
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color.{Cyan, SandyBrown}

class CellView(val canvas: Canvas, val a: Double, val b: Double) extends CellObserver {

  val cellWidth: Double = 40.0

  val cellHeight: Double = 40.0

  override def update(eventType: CellState): Unit = {

    def prepare = {
      canvas.graphicsContext2D.setFill(Cyan)
      canvas.graphicsContext2D.setStroke(SandyBrown)
      canvas.graphicsContext2D.setLineWidth(1)
    }

    eventType match {
      case CellState.AllWallBuilt => {
        prepare
        val startX = a * cellHeight
        val endX = startX + cellHeight
        val startY = b * cellWidth
        val endY = startY
        canvas.graphicsContext2D.strokeLine(startX, startY, endX, endY)
        canvas.graphicsContext2D.strokeLine(startX, startY + cellHeight, endX, endY + cellWidth) //bottom
        canvas.graphicsContext2D.strokeLine(startX + cellHeight, startY, endX, endY + cellWidth) //right
        canvas.graphicsContext2D.strokeLine(startX, startY, startX, endY + cellWidth) //left
      }
      case CellState.TopWallRuin => {
        prepare

      }

      case CellState.RightWallRuin => {
        prepare

      }

      case CellState.BottomWallRuin => {
        prepare

      }

      case CellState.LeftWallRuin => {
        prepare

      }
    }
  }
}
