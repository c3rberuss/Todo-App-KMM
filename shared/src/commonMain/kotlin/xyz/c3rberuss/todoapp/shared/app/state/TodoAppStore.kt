package xyz.c3rberuss.todoapp.shared.app.state

import com.freeletics.flowredux.dsl.ChangedState
import com.freeletics.flowredux.dsl.FlowReduxStateMachine
import com.freeletics.flowredux.dsl.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import xyz.c3rberuss.todoapp.shared.app.data.TodoRepository

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class TodoAppStore constructor(private val repository: TodoRepository) :
    FlowReduxStateMachine<TodoAppState, TodoAction>(initialState = TodoAppState.Initial) {


    init {
        spec {
            inState<TodoAppState.Initial> {
                on<TodoAction.LoadTasks>{ _, state ->
                    state.override { TodoAppState.LoadingTasks }
                }
            }

            inState<TodoAppState.LoadingTasks> {
                onEnter { state ->
                    delay(1000L)
                    val tasks = repository.getAllTasks()

                    state.override {
                        if (tasks.isEmpty()) TodoAppState.EmptyTasks else TodoAppState.ShowAllTask(
                            tasks
                        )
                    }
                }
            }

            inState<TodoAppState.ShowAllTask> {

                on<TodoAction.DeleteTask> { action, state ->
                    repository.deleteTask(action.taskId)

                    state.override {
                        TodoAppState.LoadingTasks
                    }
                }

                on(handler = this@TodoAppStore::saveTask)
            }

            inState<TodoAppState.EmptyTasks> {
                on(handler = this@TodoAppStore::saveTask)
            }
        }
    }

    private suspend fun saveTask(action: TodoAction.SaveTask, state: State<*>) : ChangedState<TodoAppState>{
        val lastPosition = repository.getLastPosition()
        val newTask = action.task.copy(position = lastPosition + 1)
        repository.saveTasks(newTask)

        return state.override {
            TodoAppState.LoadingTasks
        }
    }
}