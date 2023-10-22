package pro.jsandoval.architecturepatterns.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pro.jsandoval.architecturepatterns.data.database.dao.TodoDao
import pro.jsandoval.architecturepatterns.data.database.entity.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}