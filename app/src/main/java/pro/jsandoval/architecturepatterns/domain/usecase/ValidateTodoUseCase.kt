package pro.jsandoval.architecturepatterns.domain.usecase

import pro.jsandoval.architecturepatterns.domain.model.Todo
import javax.inject.Inject

interface ValidateTodoUseCase {

    operator fun invoke(todo: Todo): Result

    sealed class Result {
        data class Success(val todo: Todo) : Result()
        data class Error(val errorField: ErrorField) : Result()
    }

    enum class ErrorField {
        TITLE, DESCRIPTION
    }
}

class ValidateTodoUseCaseImpl @Inject constructor() : ValidateTodoUseCase {

    override fun invoke(todo: Todo): ValidateTodoUseCase.Result {
        if (todo.title.isBlank()) {
            return ValidateTodoUseCase.Result.Error(ValidateTodoUseCase.ErrorField.TITLE)
        }
        if (todo.description.isBlank()) {
            return ValidateTodoUseCase.Result.Error(ValidateTodoUseCase.ErrorField.DESCRIPTION)
        }

        return ValidateTodoUseCase.Result.Success(todo)
    }
}