package xyz.c3rberuss.todoapp.shared

import xyz.c3rberuss.todoapp.shared.app.data.TasksLocalDataSource
import xyz.c3rberuss.todoapp.shared.app.data.TodoRepository
import xyz.c3rberuss.todoapp.shared.app.domain.Task
import xyz.c3rberuss.todoapp.shared.database.DatabaseDriverFactory
import xyz.c3rberuss.todoapp.shared.database.TaskLocalDataSourceImpl
import xyz.c3rberuss.todoapp.shared.database.TodoDatabase

class TodoAppSDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = TodoDatabase(databaseDriverFactory)
    private val localDataSource: TasksLocalDataSource = TaskLocalDataSourceImpl(database)

    private val repository = TodoRepository(localDataSource)


    suspend fun getAllTasks(): List<Task> = repository.getAllTasks()

    suspend fun saveTask(task: Task) = repository.saveTasks(task)

    suspend fun deleteTask(taskId: Int) = repository.deleteTask(taskId)

    suspend fun setTaskPosition(taskId: Int, newPosition: Int) =
        repository.setTaskPosition(taskId, newPosition)

}