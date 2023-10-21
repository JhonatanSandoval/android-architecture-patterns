package pro.jsandoval.architecturepatterns.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoListBinding

class TodoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupViews() = with(binding) {
        addButton.setOnClickListener {  }
    }

    private fun setupBinding() {
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }
}