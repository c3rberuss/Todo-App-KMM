package xyz.c3rberuss.todoapp.shared.app.data

import xyz.c3rberuss.todoapp.shared.app.domain.Task

internal class TodoRepository(private val tasksLocalDataSource: TasksLocalDataSource) {
    suspend fun getAllTasks(): List<Task> = tasksLocalDataSource.getAllTasks()

    suspend fun saveTasks(task: Task) = tasksLocalDataSource.saveTask(task)

    suspend fun deleteTask(taskId: Int) = tasksLocalDataSource.deleteTask(taskId)

    suspend fun setTaskPosition(taskId: Int, newPosition: Int) =
        tasksLocalDataSource.setTaskPosition(taskId, newPosition)

    suspend fun getLastPosition() = tasksLocalDataSource.getLastPosition()
}