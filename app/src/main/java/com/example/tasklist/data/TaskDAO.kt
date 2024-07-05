package com.example.tasklist.data

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.tasklist.utils.DatabaseManager


//TaskDAO es una clase que actúa como un Data Access Object (DAO) para la tabla Task.
//databaseManager es una instancia de DatabaseManager que se utiliza para obtener acceso a la base de datos.
//TaskDAO que proporciona métodos para interactuar con la base de datos mediante operaciones
//CRUD (Crear, Leer, Actualizar y Eliminar) en la tabla Task.
class TaskDAO(context: Context){

    private val databaseManager: DatabaseManager = DatabaseManager(context)

    //insert inserta una nueva tarea en la base de datos.
    //writableDatabase obtiene una instancia de la base de datos en modo escritura.
    //ContentValues almacena los valores de las columnas name y done de la tarea.
    //db.insert inserta una nueva fila en la tabla Task y devuelve el ID de la nueva fila.
    //task.id se actualiza con el ID generado por la base de datos.
    fun insert(task: Task) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Task.COLUMN_NAME_TITLE, task.name)
        values.put(Task.COLUMN_NAME_DONE, task.done)

        val newRowId = db.insert(Task.TABLE_NAME, null, values)
        task.id = newRowId.toInt()

        db.close()
    }

    //update actualiza una tarea existente en la base de datos.
    //ContentValues almacena los nuevos valores para las columnas name y done.
    //db.update actualiza la fila en la tabla Task donde el id coincide con task.id.
    fun update(task: Task) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Task.COLUMN_NAME_TITLE, task.name)
        values.put(Task.COLUMN_NAME_DONE, task.done)

        val updatedRows = db.update(
            Task.TABLE_NAME,
            values,
            "${BaseColumns._ID} = ${task.id}",
            null
        )

        db.close()
    }

    //delete elimina una tarea de la base de datos.
    //db.delete elimina la fila en la tabla Task donde el id coincide con task.id.
    fun delete(task: Task) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Task.TABLE_NAME, "${BaseColumns._ID} = ${task.id}", null)

        db.close()
    }

    //find busca una tarea por su ID.
    //readableDatabase obtiene una instancia de la base de datos en modo lectura.
    //db.query realiza una consulta en la tabla Task para buscar la fila con el ID especificado.
    //Si se encuentra una fila, se crea un objeto Task con los valores de la fila.
    fun find(id: Int): Task? {

        val db = databaseManager.writableDatabase

        val projection = arrayOf(BaseColumns._ID, Task.COLUMN_NAME_TITLE, Task.COLUMN_NAME_DONE)

        val cursor = db.query(
            Task.TABLE_NAME,                        // The table to query
            projection,                             // The array of columns to return (pass null to get all)
            "${BaseColumns._ID} = $id",      // The columns for the WHERE clause
            null,                         // The values for the WHERE clause
            null,                            // don't group the rows
            null,                             // don't filter by row groups
            null                             // The sort order
        )
        var task: Task? = null
        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_TITLE))
            val done = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DONE)) == 1
            task = Task(id, name, done)
        }
        cursor.close()
        db.close()
        return task
    }

    //findAll obtiene todas las tareas de la base de datos.
    //db.query realiza una consulta en la tabla Task para obtener todas las filas.
    //Cada fila se convierte en un objeto Task y se agrega a una lista.
    //La lista de tareas se devuelve.
    fun findAll() : List<Task> {
        val db = databaseManager.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Task.COLUMN_NAME_TITLE, Task.COLUMN_NAME_DONE)

        val cursor = db.query(
            Task.TABLE_NAME,                        // The table to query
            projection,                             // The array of columns to return (pass null to get all)
            null,                            // The columns for the WHERE clause
            null,                         // The values for the WHERE clause
            null,                            // don't group the rows
            null,                             // don't filter by row groups
            "${Task.COLUMN_NAME_DONE} ASC"                            // The sort order
        )

        var tasks = mutableListOf<Task>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_TITLE))
            val done = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DONE)) == 1
            val task = Task(id, name, done)
            tasks.add(task)
        }
        cursor.close()
        db.close()
        return tasks
    }
}

//Resumen
//La clase TaskDAO proporciona métodos para realizar operaciones CRUD en la tabla Task de la base de datos.
//Estos métodos incluyen insert, update, delete, find y findAll, cada uno de los cuales utiliza DatabaseManager
//para interactuar con la base de datos SQLite. Aquí está la implementación completa de la clase TaskDAO: