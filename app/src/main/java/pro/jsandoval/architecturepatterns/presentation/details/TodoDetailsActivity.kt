package pro.jsandoval.architecturepatterns.presentation.details

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pro.jsandoval.architecturepatterns.R
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoDetailsBinding
import pro.jsandoval.architecturepatterns.domain.model.Todo
import pro.jsandoval.architecturepatterns.presentation.base.BaseActivity

const val TODO_PARAM = "todo"

@AndroidEntryPoint
class TodoDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityTodoDetailsBinding

    private val viewModel: TodoDetailsViewModel by viewModels<TodoDetailsViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        validateTodoParameter()
        observeLiveData()
    }

    private fun validateTodoParameter() {
        intent?.apply {
            val todoReceived = getParcelableExtra<Todo>(TODO_PARAM)
            viewModel.onTodoReceived(todoReceived)
        }
    }

    private fun observeLiveData() {
        viewModel.todoSaved.observe(this) { finish() }
        viewModel.initialTodo.observe(this) { todo ->
            todo?.let { setInitialInfo(todo.title, todo.description) }
        }
        viewModel.titleError.observe(this) { simpleToastMessage(R.string.error_todo_title) }
        viewModel.descriptionError.observe(this) { simpleToastMessage(R.string.error_todo_description) }
    }

    private fun setInitialInfo(title: String, description: String) {
        binding.todoTitle.setText(title)
        binding.todoDescription.setText(description)
    }

    private fun setupViews() = with(binding) {
        saveButton.setOnClickListener {
            viewModel.onSaveTodo(
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