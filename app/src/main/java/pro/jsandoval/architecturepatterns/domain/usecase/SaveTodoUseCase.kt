package pro.jsandoval.architecturepatterns.domain.usecase

import pro.jsandoval.architecturepatterns.domain.model.Todo
import pro.jsandoval.architecturepatterns.domain.repository.TodoRepository
import javax.inject.Inject

interface SaveTodoUseCase {
    suspend operator fun invoke(todo: Todo)
}

class SaveTodoUseCaseImpl @Inject constructor(
    private val todoRepository: TodoRepository,
) : SaveTodoUseCase {

    override suspend fun invoke(todo: Todo) {
        if (todo.isNew) {
            todoRepository.insertTodo(todo)
        } else {
            todoRepository.updateTodo(todo)
        }
    }
}