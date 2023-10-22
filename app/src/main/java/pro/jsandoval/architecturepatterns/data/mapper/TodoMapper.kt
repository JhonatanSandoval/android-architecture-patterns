package pro.jsandoval.architecturepatterns.data.mapper

import pro.jsandoval.architecturepatterns.data.database.entity.TodoEntity
import pro.jsandoval.architecturepatterns.domain.model.Todo

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