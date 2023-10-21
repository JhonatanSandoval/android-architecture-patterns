package pro.jsandoval.architecturepatterns.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pro.jsandoval.architecturepatterns.database.dao.TodoDao
import pro.jsandoval.architecturepatterns.database.entity.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}