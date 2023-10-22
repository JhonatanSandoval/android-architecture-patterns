package pro.jsandoval.architecturepatterns.details

import pro.jsandoval.architecturepatterns.database.TodoDatabaseRetriever
import pro.jsandoval.architecturepatterns.mapper.toTodoEntity
import pro.jsandoval.architecturepatterns.model.Todo

class TodoDetailsPresenter(
    private val view: TodoDetailsContract.View,
) : TodoDetailsContract.Presenter {

    private val todoDao by lazy { TodoDatabaseRetriever.getDatabase().todoDao }

    private var todo: Todo? = null
    private var isEditing: Boolean = false

    override fun setTodoReceived(todoReceived: Todo?) {
        this.todo = todoReceived ?: Todo.create()
        this.isEditing = todoReceived != null
        view.setInitialInfo(todo!!.title, todo!!.description)
    }

    override fun saveTodoInfo(title: String, description: String) {
        todo?.let { todoToSaved ->
            todoToSaved.title = title
            todoToSaved.description = description

            if (isValidToSaved(todoToSaved)) {
                val entity = todoToSaved.toTodoEntity()
                if (isEditing) {
                    todoDao.update(entity)
                } else {
                    todoDao.insert(entity)
                }
                view.todoSaved()
            }
        }
    }

    private fun isValidToSaved(todo: Todo): Boolean =
        todo.title.isNotBlank() && todo.description.isNotBlank()
}