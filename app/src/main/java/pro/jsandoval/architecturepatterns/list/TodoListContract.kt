package pro.jsandoval.architecturepatterns.list

import pro.jsandoval.architecturepatterns.model.Todo

interface TodoListContract {
    interface View {
        fun showTodoList(todos: List<Todo>)
    }

    interface Presenter {
        fun fetchTodoList()
        fun deleteTodo(todo: Todo)
    }
}