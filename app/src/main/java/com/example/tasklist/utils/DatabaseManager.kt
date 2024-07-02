package com.example.tasklist.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tasklist.data.Task

// SQLiteOpenHelper, una clase de Android que facilita la gestión de
// bases de datos SQLite. La clase DatabaseManager se encarga de crear,
// actualizar y manejar la base de datos para la aplicación.
//**********************
//La clase DatabaseManager hereda de SQLiteOpenHelper.
//El constructor de SQLiteOpenHelper toma cuatro parámetros:el contexto,
//el nombre de la base de datos, un cursor factory (en este caso es null),
//y la versión de la base de datos.
class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){


    //El bloque companion object es similar a los miembros estáticos en otros lenguajes. Permite definir constantes y métodos estáticos.
    //DATABASE_VERSION es la versión actual de la base de datos. Si cambias la estructura de la base de datos, debes incrementar este número.
    //DATABASE_NAME es el nombre del archivo de la base de datos.
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION =1
        const val DATABASE_NAME = "TaskList.db"
    }

    // Implementación para crear tablas de la base de datos

    // Implementación para crear tablas de la base de datos
    //onCreate se llama cuando la base de datos es creada por primera vez.
    //db.execSQL(Task.SQL_CREATE_TABLE) ejecuta una sentencia SQL para crear las tablas
    //necesarias. Task.SQL_CREATE_TABLE es una constante que contiene la sentencia SQL para crear la tabla
    //(probablemente definida en la clase Task).
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Task.SQL_CREATE_TABLE)
    }

    // Implementación para actualizar la base de datos

    //onUpgrade se llama cuando se incrementa DATABASE_VERSION.
    //db.execSQL(Task.SQL_DROP_TABLE) elimina las tablas existentes.
    //Task.SQL_DROP_TABLE es una constante que contiene la sentencia SQL para eliminar la tabla.
    //Luego, onCreate(db) se llama para volver a crear las tablas con la nueva estructura.
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newversion: Int) {
        db.execSQL(Task.SQL_DROP_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}

//Con esta implementación, DatabaseManager gestiona la creación, actualización y degradación
//de la base de datos de manera eficiente, garantizando que la estructura de la base de datos siempre
//esté actualizada con la versión definida.