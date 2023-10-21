package pro.jsandoval.architecturepatterns.details

import pro.jsandoval.architecturepatterns.database.TodoDatabaseRetriever
import pro.jsandoval.architecturepatterns.mapper.toTodoEntity
import pro.jsandoval.architecturepatterns.model.Todo

class TodoDetailsPresenter(
    private val view: TodoDetailsContract.View,
) : TodoDetailsContract.Presenter {

    private val todoDao by lazy { TodoDatabaseRetriever.getDatabase().todoDao }

    private var todo: Todo? = null

    override fun setTodoReceived(todoReceived: Todo?) {
        this.todo = todoReceived ?: Todo.create()
        view.setInitialInfo(todo!!.title, todo!!.description)
    }

    override fun saveTodoInfo(title: String, description: String) {
        todo?.let { todoToSaved ->
            todoToSaved.title = title
            todoToSaved.description = description

            val entity = todoToSaved.toTodoEntity()
            val isNewTodo = todoToSaved.id == 0L
            if (isNewTodo) {
                todoDao.insert(entity)
            } else {
                todoDao.update(entity)
            }
            view.todoSaved()
        }
    }
}