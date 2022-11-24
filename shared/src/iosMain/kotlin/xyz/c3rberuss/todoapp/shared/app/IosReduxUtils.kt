package xyz.c3rberuss.todoapp.shared.app

import xyz.c3rberuss.todoapp.shared.app.state.TodoAppStore

fun TodoAppStore.watchState() = state.wrap()