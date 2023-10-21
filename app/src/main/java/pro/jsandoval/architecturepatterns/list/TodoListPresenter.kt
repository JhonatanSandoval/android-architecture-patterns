package pro.jsandoval.architecturepatterns.list

import pro.jsandoval.architecturepatterns.database.TodoDatabaseRetriever
import pro.jsandoval.architecturepatterns.mapper.toTodo
import pro.jsandoval.architecturepatterns.mapper.toTodoEntity
import pro.jsandoval.architecturepatterns.model.Todo

class TodoListPresenter(
    private val view: TodoListContract.View,
) : TodoListContract.Presenter {

    private val todoDao by lazy { TodoDatabaseRetriever.getDatabase().todoDao }

    override fun fetchTodoList() {
        val todos = todoDao.getAll()
        val todosMapped = todos.map { it.toTodo() }
        view.showTodoList(todosMapped)
    }

    override fun deleteTodo(todo: Todo) {
        todoDao.delete(todo.toTodoEntity())
        fetchTodoList()
    }
}