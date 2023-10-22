package pro.jsandoval.architecturepatterns.domain.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    @ViewModelScoped
    fun bindGetTodoLiseUseCase(impl: GetTodoListUseCaseImpl): GetTodoListUseCase

    @Binds
    @ViewModelScoped
    fun bindDeleteTodoUseCase(impl: DeleteTodoUseCaseImpl): DeleteTodoUseCase

    @Binds
    @ViewModelScoped
    fun bindSaveTodoUseCase(impl: SaveTodoUseCaseImpl): SaveTodoUseCase

    @Binds
    @ViewModelScoped
    fun bindValidateTodoUseCase(impl: ValidateTodoUseCaseImpl): ValidateTodoUseCase
}