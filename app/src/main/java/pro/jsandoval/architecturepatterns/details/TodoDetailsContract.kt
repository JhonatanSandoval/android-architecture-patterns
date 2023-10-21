package pro.jsandoval.architecturepatterns.details

import pro.jsandoval.architecturepatterns.model.Todo

interface TodoDetailsContract {
    interface View {
        fun setInitialInfo(title: String, description: String)
        fun todoSaved()
    }

    interface Presenter {
        fun setTodoReceived(todo: Todo?)
        fun saveTodoInfo(title: String, description: String)
    }
}