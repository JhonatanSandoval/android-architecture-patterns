package pro.jsandoval.architecturepatterns.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pro.jsandoval.architecturepatterns.data.database.TodoDatabase
import pro.jsandoval.architecturepatterns.data.mapper.toTodo
import pro.jsandoval.architecturepatterns.data.mapper.toTodoEntity
import pro.jsandoval.architecturepatterns.domain.model.Todo
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoDatabase: TodoDatabase,
) : ViewModel() {

    private val todoDao by lazy { todoDatabase.todoDao }

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    fun fetchTodoList() {
        val todos = todoDao.getAll()
        val todosMapped = todos.map { it.toTodo() }
        _todos.value = todosMapped
    }

    fun deleteTodo(todo: Todo) {
        todoDao.delete(todo.toTodoEntity())
        fetchTodoList()
    }
}