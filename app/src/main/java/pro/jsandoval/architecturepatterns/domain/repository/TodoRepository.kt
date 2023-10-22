package pro.jsandoval.architecturepatterns.domain.repository

import kotlinx.coroutines.flow.Flow
import pro.jsandoval.architecturepatterns.domain.model.Todo

interface TodoRepository {

    fun getTodoList(): Flow<List<Todo>>
    suspend fun insertTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}