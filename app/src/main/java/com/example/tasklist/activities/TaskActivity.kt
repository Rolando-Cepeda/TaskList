package com.example.tasklist.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskDAO
import com.example.tasklist.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding

    private lateinit var taskDAO: TaskDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDAO = TaskDAO(this)

        binding.saveButtom.setOnClickListener {
            val taskName = binding.nameEditText.text.toString()
            val task = Task(-1, taskName)
            taskDAO.insert(task)
            Toast.makeText(this, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}