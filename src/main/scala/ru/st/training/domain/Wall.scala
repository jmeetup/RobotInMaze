package ru.st.training.domain

class Wall(var status: Boolean = true) {

  def isBuilt: Boolean = status

  def build: Unit = {
    status = true
  }

  def ruin: Unit = {
    status = false
  }
}
