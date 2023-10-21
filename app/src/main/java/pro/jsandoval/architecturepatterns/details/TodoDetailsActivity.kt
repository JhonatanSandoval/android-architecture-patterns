package pro.jsandoval.architecturepatterns.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoDetailsBinding
import pro.jsandoval.architecturepatterns.model.Todo

const val TODO_PARAM = "todo"

class TodoDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        validateTodoParameter()
    }

    private fun validateTodoParameter() {
        intent?.apply {
            getParcelableExtra<Todo>(TODO_PARAM)?.let { todoReceived -> handleTodoReceived(todoReceived) }
        }
    }

    private fun handleTodoReceived(todo: Todo) {
        binding.todoTitle.setText(todo.title)
        binding.todoDescription.setText(todo.description)
    }

    private fun setupViews() = with(binding) {
        saveButton.setOnClickListener {

        }
    }

    private fun setupBinding() {
        binding = ActivityTodoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }
}