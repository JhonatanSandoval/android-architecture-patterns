package pro.jsandoval.architecturepatterns.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pro.jsandoval.architecturepatterns.database.entity.TodoEntity

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: TodoEntity)

    @Update
    fun update(entity: TodoEntity)

    @Delete
    fun delete(entity: TodoEntity)

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAll(): List<TodoEntity>
}