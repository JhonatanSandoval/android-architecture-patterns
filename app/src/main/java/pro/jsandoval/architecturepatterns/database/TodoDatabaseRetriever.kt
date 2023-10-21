package pro.jsandoval.architecturepatterns.database

import android.content.Context
import androidx.room.Room

object TodoDatabaseRetriever {

    private var todoDatabase: TodoDatabase? = null

    fun init(context: Context) {
        if (todoDatabase == null) {
            todoDatabase = Room.databaseBuilder(context, TodoDatabase::class.java, "todo-db")
                .allowMainThreadQueries()
                .build()
        }
    }

    fun getDatabase(): TodoDatabase = requireNotNull(todoDatabase) { "'init' method must be called first" }
}