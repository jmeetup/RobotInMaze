package ru.st.training.domain

case class Coordinates(x: Int, y: Int) {
  def canEqual(other: Any): Boolean = other.isInstanceOf[Coordinates]
}
