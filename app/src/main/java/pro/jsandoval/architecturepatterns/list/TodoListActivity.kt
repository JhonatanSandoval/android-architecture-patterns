package pro.jsandoval.architecturepatterns.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import pro.jsandoval.architecturepatterns.databinding.ActivityTodoListBinding
import pro.jsandoval.architecturepatterns.details.TODO_PARAM
import pro.jsandoval.architecturepatterns.details.TodoDetailsActivity
import pro.jsandoval.architecturepatterns.model.Todo

class TodoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding

    private val todoListAdapter by lazy { TodoListAdapter(onItemClicked = { todo -> openTodoDetailsActivity(todo) }) }
    private val controller by lazy { TodoListController(context = this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
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

    private fun handleTodoList(todos: List<Todo>) {
        todoListAdapter.submitList(todos)
        binding.todoList.isVisible = todos.isNotEmpty()
        binding.noTodoListYet.isVisible = todos.isEmpty()
    }

    override fun onResume() {
        super.onResume()
        handleTodoList(controller.getCurrentTodoList())
    }

    private fun setupBinding() {
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }
}