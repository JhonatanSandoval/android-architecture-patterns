package pro.jsandoval.architecturepatterns.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo var title: String,
    @ColumnInfo var description: String,
)
