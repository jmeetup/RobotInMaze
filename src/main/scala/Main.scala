import ru.st.training.Maze

/**
  * Created by mr.zoom on 29.03.2016.
  */
object Main extends App{
  println("Hi Maze")
  val source = scala.io.Source.fromFile("maze.txt")
  val lines = try source.getLines().toArray finally source.close()
  val mazeSize = lines(0)
  val width = mazeSize.charAt(0).toInt
  val height = mazeSize.charAt(2).toInt
  val maze = new Maze(width, height)
  var i = 0
  val locations =
  for(i <- 1 until lines.length) {
    println(lines(i))
  }
  println("Good by")

}
