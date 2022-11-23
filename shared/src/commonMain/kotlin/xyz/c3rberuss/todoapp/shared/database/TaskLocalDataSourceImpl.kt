package xyz.c3rberuss.todoapp.shared.database

import xyz.c3rberuss.todoapp.shared.app.data.TasksLocalDataSource
import xyz.c3rberuss.todoapp.shared.app.domain.Task

internal class TaskLocalDataSourceImpl(private val todoDatabase: TodoDatabase) :
    TasksLocalDataSource {
    override suspend fun getAllTasks(): List<Task> {
        return todoDatabase.getAllTasks().map {
            it.toDomain()
        }
    }

    override suspend fun saveTask(task: Task) {
        todoDatabase.saveTask(task)
    }

    override suspend fun deleteTask(taskId: Int) {
        todoDatabase.deleteTask(taskId)
    }

    override suspend fun setTaskPosition(taskId: Int, newPosition: Int) {
        todoDatabase.setTaskPosition(id = taskId, newPosition = newPosition)
    }

    override suspend fun getLastPosition(): Int {
        TODO("Not yet implemented")
    }
}