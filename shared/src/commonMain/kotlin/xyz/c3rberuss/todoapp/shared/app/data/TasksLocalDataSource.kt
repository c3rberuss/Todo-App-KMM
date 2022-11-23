package xyz.c3rberuss.todoapp.shared.app.data

import xyz.c3rberuss.todoapp.shared.app.domain.Task

internal interface TasksLocalDataSource {
    suspend fun getAllTasks(): List<Task>
    suspend fun saveTask(task: Task)
    suspend fun deleteTask(taskId: Int)
    suspend fun setTaskPosition(taskId: Int, newPosition: Int)
    suspend fun getLastPosition() : Int
}