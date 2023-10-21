package pro.jsandoval.architecturepatterns.mapper

import pro.jsandoval.architecturepatterns.database.entity.TodoEntity
import pro.jsandoval.architecturepatterns.model.Todo

fun TodoEntity.toTodo(): Todo {
    return Todo(
        id = this.id,
        title = this.title,
        description = this.description,
    )
}

fun Todo.toTodoEntity(): TodoEntity {
    return TodoEntity(
        id = this.id,
        title = this.title,
        description = this.description,
    )
}