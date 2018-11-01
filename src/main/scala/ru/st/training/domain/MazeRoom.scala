package ru.st.training.domain

case class MazeRoom() {
  val topWall = new Wall()
  val rightWall = new Wall()
  val bottomWall = new Wall()
  val leftWall = new Wall()
}
