package pro.jsandoval.architecturepatterns.presentation.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun simpleToastMessage(resId: Int) {
        simpleToastMessage(getString(resId))
    }

    fun simpleToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}