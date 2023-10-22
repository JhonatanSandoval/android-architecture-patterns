package pro.jsandoval.architecturepatterns.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pro.jsandoval.architecturepatterns.databinding.ItemTodoListBinding
import pro.jsandoval.architecturepatterns.domain.model.Todo

class TodoListAdapter(
    private val onItemClicked: (Todo) -> Unit,
    private val onDelete: (Todo) -> Unit,
) : ListAdapter<Todo, TodoItemViewHolder>(TodoItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val binding = ItemTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item, onItemClicked, onDelete)
    }
}

object TodoItemDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem.id == newItem.id
}

class TodoItemViewHolder(
    private val binding: ItemTodoListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(todo: Todo, onItemClicked: (Todo) -> Unit, onDelete: (Todo) -> Unit) {
        binding.root.setOnClickListener { onItemClicked.invoke(todo) }
        binding.root.setOnLongClickListener {
            onDelete.invoke(todo)
            true
        }
        binding.todoTitle.text = todo.title
        binding.todoDescription.text = todo.description
    }
}