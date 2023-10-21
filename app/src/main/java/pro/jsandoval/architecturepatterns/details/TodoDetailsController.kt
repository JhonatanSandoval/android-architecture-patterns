package pro.jsandoval.architecturepatterns.details

import android.content.Context
import pro.jsandoval.architecturepatterns.database.TodoDatabaseRetriever
import pro.jsandoval.architecturepatterns.database.entity.TodoEntity
import pro.jsandoval.architecturepatterns.model.Todo

class TodoDetailsController(
    private val context: Context,
) {

    private val database by lazy { TodoDatabaseRetriever.getDatabase(context) }

    private var todo: Todo? = null

    fun setTodoReceived(todo: Todo) {
        this.todo = todo
    }

    fun saveTodo(title: String, description: String) {
        todo?.let {
            it.title = title
            it.description = description
            updateTodo(it)
        } ?: run {
            todo = Todo(id = 0, title = title, description = description)
            insertTodo(todo!!)
        }
    }

    private fun updateTodo(todo: Todo) {
        val todoEntity = TodoEntity(
            id = todo.id,
            title = todo.title,
            description = todo.description,
        )
        database.todoDao.update(todoEntity)
    }

    private fun insertTodo(todo: Todo) {
        val todoEntity = TodoEntity(
            id = todo.id,
            title = todo.title,
            description = todo.description,
        )
        database.todoDao.insert(todoEntity)
    }

}