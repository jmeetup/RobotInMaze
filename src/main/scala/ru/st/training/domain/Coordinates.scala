package ru.st.training.domain

class Coordinates(val x: Int, val y: Int) {
    def getX: Int = x
    def getY: Int = y


  def canEqual(other: Any): Boolean = other.isInstanceOf[Coordinates]

  override def equals(other: Any): Boolean = other match {
    case that: Coordinates =>
      (that canEqual this) &&
        x == that.x &&
        y == that.y
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(x, y)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
