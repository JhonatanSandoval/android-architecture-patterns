package pro.jsandoval.architecturepatterns

import android.app.Application
import pro.jsandoval.architecturepatterns.database.TodoDatabaseRetriever

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        TodoDatabaseRetriever.init(this)
    }
}