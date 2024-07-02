package com.example.tasklist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskDAO



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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Se crea una instancia de TaskDAO pasando el contexto actual (this),
        // que probablemente maneje operaciones de base de datos.
        val taskDAO = TaskDAO(this)

        // Se crea una nueva tarea (Task) con un ID de -1, el título
        // "Comprar leche" y un estado de false (no completada).
        var task = Task(-1, "Comprar leche", false)


        // La tarea se inserta en la base de datos mediante el
        // método insert del TaskDAO.
        taskDAO.insert(task)

        // Se registra la información de la tarea en el
        // log con una etiqueta "DATABASE".
        Log.i("DATABASE", task.toString())


        // Se marca la tarea como completada (task.done = true).
        //Se actualiza la tarea en la base de datos mediante el método update del TaskDAO.
        //Se registra la información actualizada de la tarea en el log.
        task.done = true
        taskDAO.update(task)
        Log.i("DATABASE", task.toString())


        //Se busca la tarea en la base de datos usando su ID y se asigna a task.
        //Se registra la información de la tarea recuperada en el log.
        task = taskDAO.find(task.id)!!
        Log.i("DATABASE", task.toString())

        //Se elimina la tarea de la base de datos mediante el
        // método delete del TaskDAO.
        taskDAO.delete(task)


        //Se recupera una lista de todas las tareas de la base de datos mediante el método findAll del TaskDAO.
        //Se registra la lista de tareas en el log.
        val taskList = taskDAO.findAll()
        Log.i("DATABASE", taskList.toString())

        //Se busca un botón en el diseño con el ID button y se le asigna un OnClickListener.
        //Cuando el botón es presionado, se llama al método findAll del TaskDAO.
        findViewById<Button>(R.id.button).setOnClickListener {
            taskDAO.findAll()
        }
    }
}