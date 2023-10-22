package pro.jsandoval.architecturepatterns.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import pro.jsandoval.architecturepatterns.R
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoListBinding
import pro.jsandoval.architecturepatterns.details.TODO_PARAM
import pro.jsandoval.architecturepatterns.details.TodoDetailsActivity
import pro.jsandoval.architecturepatterns.model.Todo

class TodoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding

    private val todoListAdapter by lazy {
        TodoListAdapter(
            onItemClicked = { todo -> openTodoDetailsActivity(todo) },
            onDelete = { todo -> showDeleteDialog(todo) }
        )
    }

    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.todos.observe(this, ::showTodoList)
    }

    private fun setupViews() = with(binding) {
        addButton.setOnClickListener { openTodoDetailsActivity(todo = null) }
        todoList.adapter = todoListAdapter
    }

    private fun openTodoDetailsActivity(todo: Todo?) {
        val intent = Intent(this, TodoDetailsActivity::class.java).apply {
            putExtra(TODO_PARAM, todo)
        }
        startActivity(intent)
    }

    private fun showDeleteDialog(todo: Todo) {
        AlertDialog.Builder(this)
            .setTitle(R.string.delete_todo_title)
            .setMessage(R.string.delete_todo_description)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.yes_delete_todo) { _, _ ->
                viewModel.deleteTodo(todo)
            }
            .show()
    }

    private fun showTodoList(todos: List<Todo>) {
        todoListAdapter.submitList(todos)
        binding.todoList.isVisible = todos.isNotEmpty()
        binding.noTodoListYet.isVisible = todos.isEmpty()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchTodoList()
    }

    private fun setupBinding() {
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }
}