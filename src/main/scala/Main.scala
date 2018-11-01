import ru.st.training._
import ru.st.training.domain.Maze

/**
  * Created by mr.zoom on 29.03.2016.
  */
object Main extends App {
  val width = 20
  val height = 20
  println(width + " width")
  println(height + " heigth")
  val maze = new Maze(width, height)

}
