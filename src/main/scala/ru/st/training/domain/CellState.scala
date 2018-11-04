package ru.st.training.domain

object CellState  extends Enumeration {

  type CellState = Value

  val AllWallBuilt, TopWallRuin, RightWallRuin, BottomWallRuin, LeftWallRuin,

      AllWallBuiltWithExit = Value

}
