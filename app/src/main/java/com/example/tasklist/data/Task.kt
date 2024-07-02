package com.example.tasklist.data

import android.provider.BaseColumns


//data class Task: Declara una clase de datos llamada Task. Las clases de datos en
//Kotlin son útiles para almacenar datos y automáticamente generan métodos como equals(), hashCode(), toString(), copy(), etc.
data class Task(var id: Int, var name: String, var done: Boolean = false) {

    //El bloque companion object permite definir miembros estáticos dentro de la clase Task.
    companion object {
        // Define el nombre de la tabla en la base de datos como Tasks.
        const val TABLE_NAME ="Tasks"
        //Define los nombres de las columnas en la tabla de la base de datos. COLUMN_NAME_TITLE es el nombre de la columna que
        //almacenará el título de la tarea y COLUMN_NAME_DONE es el nombre de la columna que almacenará el estado de la
        //tarea (si está hecha o no).
        const val COLUMN_NAME_TITLE = "name"
        const val COLUMN_NAME_DONE = "done"

        // ********************************
        //Define la sentencia SQL para crear la tabla Tasks.
        //CREATE TABLE $TABLE_NAME: Crea una tabla con el nombre Tasks.
        //${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT: Crea una columna _ID que es la clave primaria y se autoincrementa.
        //$COLUMN_NAME_TITLE TEXT: Crea una columna name de tipo TEXT.
        //$COLUMN_NAME_DONE INTEGER: Crea una columna done de tipo INTEGER. En SQLite, se puede usar
        //INTEGER para representar valores booleanos (0 para false, 1 para true).
        const val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME_TITLE TEXT," +
                    "$COLUMN_NAME_DONE INTEGER"

        //Define la sentencia SQL para eliminar la tabla Tasks si existe.
        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}

//RESUMEN
//La clase Task está diseñada para representar una tarea en la aplicación con las propiedades
//id, name y done. Además, contiene un bloque companion object que define las constantes necesarias
//para interactuar con la base de datos SQLite, incluyendo el nombre de la tabla, los nombres de
//las columnas y las sentencias SQL para crear y eliminar la tabla.

//Esta implementación hace que la clase Task no solo represente una tarea en la aplicación, sino que
//también facilite la creación y gestión de la tabla correspondiente en la base de datos SQLite.
