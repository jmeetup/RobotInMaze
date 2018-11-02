package ru.st.training.domain

import ru.st.training.domain.CellState.CellState

trait CellObserver {
    def update(eventType: CellState)
}
