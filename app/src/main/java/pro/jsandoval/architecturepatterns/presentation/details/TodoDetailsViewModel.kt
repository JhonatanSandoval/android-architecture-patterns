package pro.jsandoval.architecturepatterns.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import pro.jsandoval.architecturepatterns.domain.model.Todo
import pro.jsandoval.architecturepatterns.domain.usecase.SaveTodoUseCase
import pro.jsandoval.architecturepatterns.domain.usecase.ValidateTodoUseCase
import pro.jsandoval.architecturepatterns.domain.usecase.ValidateTodoUseCase.ErrorField
import pro.jsandoval.architecturepatterns.util.SingleLiveEvent
import javax.inject.Inject

abstract class TodoDetailsViewModel : ViewModel() {
    abstract val initialTodo: LiveData<Todo>
    abstract val todoSaved: LiveData<Unit>
    abstract val titleError: LiveData<Unit>
    abstract val descriptionError: LiveData<Unit>

    abstract fun onTodoReceived(todoReceived: Todo?)
    abstract fun onSaveTodo(title: String, description: String)
}

@HiltViewModel
class TodoDetailsViewModelImpl @Inject constructor(
    private val saveTodoUseCase: SaveTodoUseCase,
    private val validateTodoUseCase: ValidateTodoUseCase,
) : TodoDetailsViewModel() {

    override val initialTodo = MutableLiveData<Todo>()
    override val todoSaved = SingleLiveEvent<Unit>()
    override val titleError = SingleLiveEvent<Unit>()
    override val descriptionError = SingleLiveEvent<Unit>()

    private lateinit var todo: Todo

    override fun onTodoReceived(todoReceived: Todo?) {
        this.todo = todoReceived ?: Todo.create()
        initialTodo.postValue(this.todo)
    }

    override fun onSaveTodo(title: String, description: String) {
        viewModelScope.launch(IO) {
            todo.title = title
            todo.description = description

            when (val validationResult = validateTodoUseCase(todo)) {
                is ValidateTodoUseCase.Result.Success -> handleValidationSuccess(validationResult)
                is ValidateTodoUseCase.Result.Error -> handleValidationError(validationResult)
            }
        }
    }

    private fun handleValidationSuccess(result: ValidateTodoUseCase.Result.Success) {
        viewModelScope.launch(IO) {
            saveTodoUseCase(result.todo)
            todoSaved.postValue(Unit)
        }
    }

    private fun handleValidationError(error: ValidateTodoUseCase.Result.Error) {
        when (error.errorField) {
            ErrorField.TITLE -> titleError.postValue(Unit)
            ErrorField.DESCRIPTION -> descriptionError.postValue(Unit)
        }
    }

}