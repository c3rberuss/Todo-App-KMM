package xyz.c3rberuss.todoapp.shared.database

import com.squareup.sqldelight.EnumColumnAdapter
import xyz.c3rberuss.todoapp.shared.app.domain.Task

internal class TodoDatabase(databaseFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(
        driver = databaseFactory.createDriver(),
        TaskEntityAdapter = TaskEntity.Adapter(
            statusAdapter = EnumColumnAdapter()
        )
    )

    private val dbQuery = database.appDatabaseQueries

    fun getAllTasks(): List<TaskEntity> {
        return dbQuery.selectAllTask().executeAsList()
    }

    fun saveTask(task: Task) {
        dbQuery.saveTask(
            name = task.name,
            description = task.description,
            position = task.position.toLong(),
            status = task.status
        )
    }

    fun deleteTask(id: Int) {
        dbQuery.deleteTaskById(id = id.toLong())
    }

    fun setTaskPosition(id: Int, newPosition: Int) {
        dbQuery.setPositionById(id = id.toLong(), position = newPosition.toLong())
    }
}