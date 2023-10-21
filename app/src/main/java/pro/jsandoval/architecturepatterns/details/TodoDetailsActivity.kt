package pro.jsandoval.architecturepatterns.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoDetailsBinding
import pro.jsandoval.architecturepatterns.model.Todo

const val TODO_PARAM = "todo"

class TodoDetailsActivity : AppCompatActivity(), TodoDetailsContract.View {

    private lateinit var binding: ActivityTodoDetailsBinding

    private val presenter: TodoDetailsContract.Presenter by lazy {
        TodoDetailsPresenter(view = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        validateTodoParameter()
    }

    private fun validateTodoParameter() {
        intent?.apply {
            val todoReceived = getParcelableExtra<Todo>(TODO_PARAM)
            presenter.setTodoReceived(todoReceived)
        }
    }

    override fun setInitialInfo(title: String, description: String) {
        binding.todoTitle.setText(title)
        binding.todoDescription.setText(description)
    }

    override fun todoSaved() = finish()

    private fun setupViews() = with(binding) {
        saveButton.setOnClickListener {
            presenter.saveTodoInfo(
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