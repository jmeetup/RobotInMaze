import ru.st.training._

/**
  * Created by mr.zoom on 29.03.2016.
  */
object Main extends App{
  println("Hi Maze")
  val source = scala.io.Source.fromFile("maze.txt")
  val lines = try source.getLines().toArray finally source.close()
  val mazeSize = lines(0)
  val width = mazeSize.charAt(0).asDigit
  val height = mazeSize.charAt(2).asDigit
  println(width + " width")
  println(height + " heigth")
  val maze = new Maze(width, height)
  var i = 0
  for(i <- 1 until lines.length) {
    maze.initLocationByCoordinate(width, i, stringToLocation(lines(i)))
    println(lines(i))
  }
  println("Good by")

  def stringToLocation(str: String): Location = {
    val top = str.charAt(0).asDigit
    val right = str.charAt(2).asDigit
    val bottom = str.charAt(4).asDigit
    val left = str.charAt(6).asDigit

    implicit def int2bool(i:Int) = if (i == 0) false else true

    Location(int2bool(top),int2bool(right),int2bool(bottom),int2bool(left))
  }


}
