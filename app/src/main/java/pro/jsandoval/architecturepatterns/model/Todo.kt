package pro.jsandoval.architecturepatterns.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val title: String,
    val description: String,
) : Parcelable
