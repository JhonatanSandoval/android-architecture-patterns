package pro.jsandoval.architecturepatterns.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pro.jsandoval.architecturepatterns.domain.model.Todo
import pro.jsandoval.architecturepatterns.domain.usecase.SaveTodoUseCase
import javax.inject.Inject

@HiltViewModel
class TodoDetailsViewModel @Inject constructor(
    private val saveTodoUseCase: SaveTodoUseCase,
) : ViewModel() {

    private val _initialTodo = MutableLiveData<Todo>()
    val initialTodo: LiveData<Todo> = _initialTodo

    private val _todoSaved = MutableLiveData<Unit>()
    val todoSaved: LiveData<Unit> = _todoSaved

    private lateinit var todo: Todo

    fun setTodoReceived(todoReceived: Todo?) {
        this.todo = todoReceived ?: Todo.create()
        _initialTodo.value = this.todo
    }

    fun saveTodoInfo(title: String, description: String) {
        todo.title = title
        todo.description = description

        if (isValidToSaved(todo)) {
            viewModelScope.launch {
                saveTodoUseCase(todo)
                _todoSaved.value = Unit
            }
        }
    }

    private fun isValidToSaved(todo: Todo): Boolean =
        todo.title.isNotBlank() && todo.description.isNotBlank()
}