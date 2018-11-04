package ru.st.training.gui


import ru.st.training.domain.{Cell, Coordinates, Maze}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

object ScalaFXHelloWorld extends JFXApp {

  private val canvasWidth = 600
  private val canvasHeight = 600
  private val canvas = new Canvas(canvasWidth, canvasHeight) {
    graphicsContext2D.setFill(Black)
  }

  var maze: Maze = null
  var isMazeInit = false


  val wInput = new TextField {

  }

  val hInput = new TextField {

  }

  val createBtn = new Button {
    thisButton =>
    text = "Create"
    padding = Insets(10)
    tooltip = "Run create algorithm"
    onAction = { _ =>
      canvas.graphicsContext2D.clearRect(0, 0, canvasWidth, canvasHeight)
      val w = wInput.text.value.toInt
      val h = hInput.text.value.toInt
      if (h > 0 && w > 0) {
        maze = new Maze(w, h)

        createCellViews(maze, w, h)
        isMazeInit = true
      }
    }
  }

  val genBtn = new Button {
    thisButton =>
    text = "Generate"
    padding = Insets(10)
    tooltip = "Run gen algorithm"
    onAction = { _ =>
      if (isMazeInit) maze.generate()
    }
  }


  def createCellViews(maze: Maze, mazeWidth: Int, mazeHeight: Int): Unit = {
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
        }, new HBox {
          padding = Insets(10)
          children = Seq(
            new Text {
              padding = Insets(10, 10, 10, 10)
              text = "Width"
              style = "-fx-font: normal bold 20pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(Red, DarkRed))
            },
            wInput
            ,
            new Text {
              padding = Insets(10, 10, 10, 10)
              text = "Height"
              style = "-fx-font: italic bold 20pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(White, DarkGray)
              )
              effect = new DropShadow {
                color = DarkGray
                radius = 15
                spread = 0.25
              }
            },
            hInput,
            createBtn,
            genBtn
          )
        },
          canvas

        )
      }
    }
  }
}