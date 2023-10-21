package pro.jsandoval.architecturepatterns.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoListBinding
import pro.jsandoval.architecturepatterns.details.TODO_PARAM
import pro.jsandoval.architecturepatterns.details.TodoDetailsActivity
import pro.jsandoval.architecturepatterns.model.Todo

class TodoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupViews() = with(binding) {
        addButton.setOnClickListener { openTodoDetailsActivity(todo = null) }
    }

    private fun openTodoDetailsActivity(todo: Todo?) {
        val intent = Intent(this, TodoDetailsActivity::class.java).apply {
            putExtra(TODO_PARAM, todo)
        }
        startActivity(intent)
    }

    private fun setupBinding() {
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }
}