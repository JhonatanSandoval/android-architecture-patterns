package pro.jsandoval.architecturepatterns.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoDetailsBinding
import pro.jsandoval.architecturepatterns.model.Todo

const val TODO_PARAM = "todo"

class TodoDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoDetailsBinding

    private val controller by lazy { TodoDetailsController(context = this) }

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
        controller.setTodoReceived(todo)
        binding.todoTitle.setText(todo.title)
        binding.todoDescription.setText(todo.description)
    }

    private fun setupViews() = with(binding) {
        saveButton.setOnClickListener {
            controller.saveTodo(
                title = binding.todoTitle.text.toString().trim(),
                description = binding.todoDescription.text.toString().trim(),
            )
            finish()
        }
    }

    private fun setupBinding() {
        binding = ActivityTodoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }
}