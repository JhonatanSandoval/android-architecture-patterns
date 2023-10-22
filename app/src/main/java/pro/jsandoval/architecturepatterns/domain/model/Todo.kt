package pro.jsandoval.architecturepatterns.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

private const val DEFAULT_TODO_ID = 0

@Parcelize
data class Todo(
    val id: Int,
    var title: String,
    var description: String,
) : Parcelable {

    val isNew: Boolean
        get() = id == DEFAULT_TODO_ID

    companion object {
        fun create(): Todo = Todo(id = DEFAULT_TODO_ID, title = "", description = "")
    }
}
