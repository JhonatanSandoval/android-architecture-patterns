package pro.jsandoval.architecturepatterns.list

import android.content.Context
import pro.jsandoval.architecturepatterns.database.TodoDatabaseRetriever
import pro.jsandoval.architecturepatterns.database.entity.TodoEntity
import pro.jsandoval.architecturepatterns.model.Todo

class TodoListController(
    private val context: Context,
) {

    private val database by lazy { TodoDatabaseRetriever.getDatabase(context) }

    fun getCurrentTodoList(): List<Todo> {
        val todos = database.todoDao.getAll()
        return todos.map { item ->
            Todo(
                id = item.id,
                title = item.title,
                description = item.description,
            )
        }
    }

    fun deleteTodo(todo: Todo) {
        val todoEntity = TodoEntity(
            id = todo.id,
            title = todo.title,
            description = todo.description,
        )
        database.todoDao.delete(todoEntity)
    }
}