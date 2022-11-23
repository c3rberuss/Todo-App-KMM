package xyz.c3rberuss.todoapp.shared.app.domain

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val status: TaskStatus,
    val position: Int,
)
