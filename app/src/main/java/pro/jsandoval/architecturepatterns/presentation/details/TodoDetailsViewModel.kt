package pro.jsandoval.architecturepatterns.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pro.jsandoval.architecturepatterns.data.database.TodoDatabase
import pro.jsandoval.architecturepatterns.data.mapper.toTodoEntity
import pro.jsandoval.architecturepatterns.domain.model.Todo
import javax.inject.Inject

@HiltViewModel
class TodoDetailsViewModel @Inject constructor(
    private val todoDatabase: TodoDatabase,
) : ViewModel() {

    private val todoDao by lazy { todoDatabase.todoDao }

    private val _initialTodo = MutableLiveData<Todo>()
    val initialTodo: LiveData<Todo> = _initialTodo

    private val _todoSaved = MutableLiveData<Unit>()
    val todoSaved: LiveData<Unit> = _todoSaved

    private lateinit var todo: Todo
    private var isEditing: Boolean = false

    fun setTodoReceived(todoReceived: Todo?) {
        this.todo = todoReceived ?: Todo.create()
        this.isEditing = todoReceived != null
        _initialTodo.value = this.todo
    }

    fun saveTodoInfo(title: String, description: String) {
        todo.title = title
        todo.description = description

        if (isValidToSaved(todo)) {
            val entity = todo.toTodoEntity()
            if (isEditing) {
                todoDao.update(entity)
            } else {
                todoDao.insert(entity)
            }
            _todoSaved.value = Unit
        }
    }

    private fun isValidToSaved(todo: Todo): Boolean =
        todo.title.isNotBlank() && todo.description.isNotBlank()
}