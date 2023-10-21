package pro.jsandoval.architecturepatterns.database

import android.content.Context
import androidx.room.Room

object TodoDatabaseRetriever {

    private var todoDatabase: TodoDatabase? = null

    fun getDatabase(context: Context): TodoDatabase {
        if (todoDatabase == null) {
            todoDatabase = Room.databaseBuilder(context, TodoDatabase::class.java, "todo-db")
                .allowMainThreadQueries()
                .build()
        }
        return todoDatabase!!
    }
}