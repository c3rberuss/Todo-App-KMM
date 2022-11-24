package xyz.c3rberuss.todoapp.shared.app.state

import xyz.c3rberuss.todoapp.shared.app.domain.Task

sealed interface TodoAction{
    object LoadTasks : TodoAction

    data class SaveTask(val task: Task) : TodoAction

    data class DeleteTask(val taskId: Int) : TodoAction
}