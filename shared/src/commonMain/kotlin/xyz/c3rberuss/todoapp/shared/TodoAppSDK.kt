package xyz.c3rberuss.todoapp.shared

import xyz.c3rberuss.todoapp.shared.app.data.TasksLocalDataSource
import xyz.c3rberuss.todoapp.shared.app.data.TodoRepository
import xyz.c3rberuss.todoapp.shared.app.state.TodoAppStore
import xyz.c3rberuss.todoapp.shared.database.DatabaseDriverFactory
import xyz.c3rberuss.todoapp.shared.database.TaskLocalDataSourceImpl
import xyz.c3rberuss.todoapp.shared.database.TodoDatabase

class TodoAppSDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = TodoDatabase(databaseDriverFactory)
    private val localDataSource: TasksLocalDataSource = TaskLocalDataSourceImpl(database)

    private val repository = TodoRepository(localDataSource)

    val store = TodoAppStore(repository)

}