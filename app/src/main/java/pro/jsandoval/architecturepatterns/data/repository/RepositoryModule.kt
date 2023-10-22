package pro.jsandoval.architecturepatterns.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.jsandoval.architecturepatterns.domain.repository.TodoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindTodoRepository(impl: TodoRepositoryImpl): TodoRepository
}