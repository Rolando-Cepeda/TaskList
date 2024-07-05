package com.example.tasklist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.R
import com.example.tasklist.data.Task
import com.example.tasklist.databinding.ItemTaskBinding

class TaskAdapter (
    private var dataSet: List<Task> = emptyList(),
    private val onItemClickListener: (Int) -> Unit,

    private val onItemDeleteClickListener: (Int) -> Unit,
    private val onItemCheckedClickListener: (Int) -> Unit
): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(dataSet[position])
        holder.itemView.setOnClickListener {
            onItemClickListener(position)
        }
        holder.binding.deleteButton.setOnClickListener {
            onItemDeleteClickListener(position)
        }

        holder.binding.doneCheckBox.setOnCheckedChangeListener { checkbox, _ ->
            if(checkbox.isPressed) {
                // Cambia el fondo de la celda cuando se hace clic en el CheckBox
                if (checkbox.isChecked) {
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.checked_color))
                } else {
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.unchecked_color))
                }
                onItemCheckedClickListener(position)
            }
        }
    }

    fun updateData(dataSet: List<Task>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}

class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(task: Task) {
        binding.nameTextView.text = task.name
        binding.doneCheckBox.isChecked = task.done
    }
}