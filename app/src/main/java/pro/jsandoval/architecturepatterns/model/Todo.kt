package pro.jsandoval.architecturepatterns.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Long,
    var title: String,
    var description: String,
) : Parcelable {

    companion object {
        fun create(): Todo = Todo(id = 0, title = "", description = "")
    }
}
