package pro.jsandoval.architecturepatterns.presentation.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoDetailsBinding
import pro.jsandoval.architecturepatterns.domain.model.Todo

const val TODO_PARAM = "todo"

@AndroidEntryPoint
class TodoDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoDetailsBinding

    private val viewModel: TodoDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        validateTodoParameter()
        observeLiveData()
    }

    private fun validateTodoParameter() {
        intent?.apply {
            val todoReceived = getParcelableExtra<Todo>(TODO_PARAM)
            viewModel.setTodoReceived(todoReceived)
        }
    }

    private fun observeLiveData() {
        viewModel.todoSaved.observe(this) { finish() }
        viewModel.initialTodo.observe(this) { todo ->
            todo?.let { setInitialInfo(todo.title, todo.description) }
        }
    }

    private fun setInitialInfo(title: String, description: String) {
        binding.todoTitle.setText(title)
        binding.todoDescription.setText(description)
    }

    private fun setupViews() = with(binding) {
        saveButton.setOnClickListener {
            viewModel.saveTodoInfo(
                title = binding.todoTitle.text.toString().trim(),
                description = binding.todoDescription.text.toString().trim(),
            )
        }
    }

    private fun setupBinding() {
        binding = ActivityTodoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }
}