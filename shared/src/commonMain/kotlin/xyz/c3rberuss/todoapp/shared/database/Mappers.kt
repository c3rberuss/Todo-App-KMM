package xyz.c3rberuss.todoapp.shared.database

import xyz.c3rberuss.todoapp.shared.app.domain.Task

internal fun TaskEntity.toDomain() = Task(
    name = name,
    description = description,
    status = status,
    position = position.toInt()
)