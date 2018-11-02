package ru.st.training.gui


import ru.st.training.domain.{Cell, Coordinates, Maze}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

object ScalaFXHelloWorld extends JFXApp {

  private val canvasWidth = 400
  private val canvasHeight = 400
  private val canvas = new Canvas(canvasWidth, canvasHeight) {
    graphicsContext2D.setFill(SandyBrown)
    graphicsContext2D.setStroke(Cyan)
    graphicsContext2D.setLineWidth(5)
    graphicsContext2D.strokeRect(0, //x of the upper left corner
      0, //y of the upper left corner
      canvasWidth, //width of the rectangle
      canvasHeight) //height of the rectangle

  }

  private val mazeWidth: Int = 5
  private val mazeHeight: Int = 5
  val maze = new Maze(mazeWidth, mazeHeight)

  //
  //  val cellView: CellView = new CellView(canvas, 10, 10)
  //  maze.getGrid.getInitialCell.addObserver(cellView)
  createCellViews(maze)
  maze.getGrid.getInitialCell.ruinLeftWall

  //  maze.generate()

  //
  //  canvas.graphicsContext2D.setFill(Cyan)
  //  canvas.graphicsContext2D.setStroke(SandyBrown)
  //  canvas.graphicsContext2D.fillRect(20.0, 20.0, 20.0, 20.0)

  def createCellViews(maze: Maze): Unit = {
    for (a <- 1 to mazeWidth; b <- 1 to mazeHeight) {
      val coordinates = new Coordinates(a, b)
      val cell: Option[Cell] = maze.getGrid.getCellByCoordinates(coordinates)
      if (cell.isDefined) {
        val cellView: CellView = new CellView(canvas, a, b)
        cell.get.addObserver(cellView)
        cell.get.currentState
      }
    }
  }

  stage = new PrimaryStage {
    title = "ScalaFX Hello World"
    scene = new Scene {
      fill = Black
      content = new VBox {
        padding = Insets(20)
        children = Seq(new HBox {
          padding = Insets(20)
          children = Seq(
            new Text {
              text = "Robot "
              style = "-fx-font-size: 38pt"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(PaleGreen, SeaGreen))
            },
            new Text {
              text = "in "
              style = "-fx-font-size: 38pt"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(PaleGreen, SandyBrown))
            },
            new Text {
              text = "Maze"
              style = "-fx-font-size: 38pt"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(Cyan, SeaShell)
              )
              effect = new DropShadow {
                color = DodgerBlue
                radius = 25
                spread = 0.25
              }
            }
          )
        },
          canvas

        )
      }
    }
  }
}