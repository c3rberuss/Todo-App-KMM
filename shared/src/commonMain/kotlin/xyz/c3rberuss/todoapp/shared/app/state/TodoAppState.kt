package xyz.c3rberuss.todoapp.shared.app.state

import xyz.c3rberuss.todoapp.shared.app.domain.Task

sealed interface TodoAppState {

    object Initial : TodoAppState

    object LoadingTasks : TodoAppState

    data class ShowAllTask(
        val tasks: List<Task>
    ) : TodoAppState

    object EmptyTasks : TodoAppState
}