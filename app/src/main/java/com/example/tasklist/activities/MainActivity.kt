package com.example.tasklist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasklist.adapters.TaskAdapter
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskDAO
import com.example.tasklist.databinding.ActivityMainBinding

//Corresponde a una actividad principal (MainActivity)
//de una aplicación de Android escrita en Kotlin.
// La actividad interactúa con una base de datos mediante las clases
// TaskDAO y DataBaseManager.

//***************************
//Aquí se define la clase MainActivity que hereda de AppCompatActivity,
// lo cual le permite comportarse como una actividad en una aplicación de Android.

//onCreate es el método que se llama cuando la actividad se está creando.
//super.onCreate llama al método onCreate del AppCompatActivity.

//setContentView(R.layout.activity_main) establece el diseño de la actividad usando
//el archivo de diseño activity_main.xml.
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: TaskAdapter
    private lateinit var taskList: List<Task>

    private lateinit var taskDAO: TaskDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDAO = TaskDAO(this)

        adapter = TaskAdapter(emptyList(), {
            Toast.makeText(this, "Click en tarea: ${taskList[it].name}", Toast.LENGTH_SHORT).show()
        }, {
            taskDAO.delete(taskList[it])
            Toast.makeText(this, "Tarea borrada correctamente", Toast.LENGTH_SHORT).show()
            loadData()
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.addTaskButton.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadData()
    }

    private fun loadData() {
        taskList = taskDAO.findAll()

        adapter.updateData(taskList)
    }
}