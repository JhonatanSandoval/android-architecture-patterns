package pro.jsandoval.architecturepatterns.domain.usecase

import pro.jsandoval.architecturepatterns.domain.model.Todo
import pro.jsandoval.architecturepatterns.domain.repository.TodoRepository
import javax.inject.Inject

interface DeleteTodoUseCase {
    suspend operator fun invoke(todo: Todo)
}

class DeleteTodoUseCaseImpl @Inject constructor(
    private val todoRepository: TodoRepository,
) : DeleteTodoUseCase {

    override suspend fun invoke(todo: Todo) {
        return todoRepository.deleteTodo(todo)
    }
}