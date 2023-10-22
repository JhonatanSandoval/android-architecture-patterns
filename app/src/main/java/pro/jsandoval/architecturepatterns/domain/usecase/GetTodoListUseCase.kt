package pro.jsandoval.architecturepatterns.domain.usecase

import kotlinx.coroutines.flow.Flow
import pro.jsandoval.architecturepatterns.domain.model.Todo
import pro.jsandoval.architecturepatterns.domain.repository.TodoRepository
import javax.inject.Inject

interface GetTodoListUseCase {
    operator fun invoke(): Flow<List<Todo>>
}

class GetTodoListUseCaseImpl @Inject constructor(
    private val todoRepository: TodoRepository,
) : GetTodoListUseCase {
    override fun invoke(): Flow<List<Todo>> {
        return todoRepository.getTodoList()
    }
}