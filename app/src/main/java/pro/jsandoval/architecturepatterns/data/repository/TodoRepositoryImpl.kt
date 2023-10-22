package pro.jsandoval.architecturepatterns.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pro.jsandoval.architecturepatterns.data.database.TodoDatabase
import pro.jsandoval.architecturepatterns.data.mapper.toTodo
import pro.jsandoval.architecturepatterns.data.mapper.toTodoEntity
import pro.jsandoval.architecturepatterns.domain.model.Todo
import pro.jsandoval.architecturepatterns.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDatabase: TodoDatabase,
) : TodoRepository {

    private val todoDao by lazy { todoDatabase.todoDao }

    override fun getTodoList(): Flow<List<Todo>> {
        return todoDao.getAll()
            .map { todoList -> todoList.map { it.toTodo() } }
    }

    override suspend fun insertTodo(todo: Todo) = todoDao.insert(todo.toTodoEntity())

    override suspend fun updateTodo(todo: Todo) = todoDao.update(todo.toTodoEntity())

    override suspend fun deleteTodo(todo: Todo) = todoDao.delete(todo.toTodoEntity())
}