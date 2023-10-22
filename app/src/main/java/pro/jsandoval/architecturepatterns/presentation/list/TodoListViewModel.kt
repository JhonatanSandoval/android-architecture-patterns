package pro.jsandoval.architecturepatterns.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import pro.jsandoval.architecturepatterns.domain.model.Todo
import pro.jsandoval.architecturepatterns.domain.usecase.DeleteTodoUseCase
import pro.jsandoval.architecturepatterns.domain.usecase.GetTodoListUseCase
import javax.inject.Inject

abstract class TodoListViewModel : ViewModel() {
    abstract val todos: LiveData<List<Todo>>

    abstract fun onDeleteTodo(todo: Todo)
}

@HiltViewModel
class TodoListViewModelImpl @Inject constructor(
    private val getTodoListUseCase: GetTodoListUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
) : TodoListViewModel() {

    override val todos = MutableLiveData<List<Todo>>()

    init {
        fetchTodoList()
    }

    private fun fetchTodoList() = viewModelScope.launch(IO) {
        getTodoListUseCase().collect { todos ->
            this@TodoListViewModelImpl.todos.postValue(todos)
        }
    }

    override fun onDeleteTodo(todo: Todo) {
        viewModelScope.launch(IO) {
            deleteTodoUseCase(todo)
        }
    }
}