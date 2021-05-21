package com.istea.dao

import android.app.Person
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.istea.dto.Persona
import java.lang.Exception

class DBHelper(context: Context,factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION)
{
    companion object{
        private val DATABASE_NAME ="databasejueves.db"
        private val DATABASE_VERSION=1

        val TABLE_PERSONA = "persona"
        val COLUMN_ID="id"
        val COLUMN_NOMBRE ="nombre"
        val COLUMN_DNI="dni"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE =("CREATE TABLE "+ TABLE_PERSONA+ "  (" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NOMBRE +" TEXT," +
                COLUMN_DNI+ " INTEGER) ")

        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if(oldVersion != newVersion){
            // correme el drop de la tabla y creala de nuevo
            db?.execSQL("DROP TABLE IF EXISTS "+ TABLE_PERSONA)
            onCreate(db)
        }
    }


    // insert , update , delete , select CRUD


    // INSERT
    fun savePersona(p: Persona): Boolean{

        var guardo: Boolean = false
        try {
            val db = this.writableDatabase

            // INSERT INTO TABLA (CAMPO1,CAMPO2,CAMPON) VALUES (VALOR1,VALOR2,VALORN)

            val values = ContentValues()

            values.put(COLUMN_NOMBRE,p.nombre)
            values.put(COLUMN_DNI,p.dni)

            db.insert(TABLE_PERSONA,null,values)
            guardo = true

        }catch (e:Exception){
            Log.e("Error-DATABASE",e.message.toString())
            guardo=false
        }
        return guardo
    }


    // recuperar todas las personas
    fun getAllPersonas():ArrayList<Persona>{

        val listaPersona: ArrayList<Persona> = ArrayList<Persona>()
        val db = this.readableDatabase
        val query = "SELECT * FROM "+ TABLE_PERSONA

       val cursor:Cursor =  db.rawQuery(query,null)

        if(cursor.moveToFirst()){
           do {
               val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
               val dni = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
               val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))

               listaPersona.add(Persona(nombre,dni,id))

           }while(cursor.moveToNext())
        }
        return listaPersona
    }


    fun getPersonaById(id: Int):Persona{

        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_PERSONA + " WHERE "+ COLUMN_ID + "=" +id
        var p: Persona? = null
        val cursor : Cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst()){

            val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
            val dni = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))

            p = Persona(nombre,dni,id)
        }
        return p!!
    }


    fun deletePersonaById(id:Int): Boolean{
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_PERSONA + " WHERE "+ COLUMN_ID + "=" +id
        var p: Persona? = null
        val cursor : Cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst()){

            db.delete(TABLE_PERSONA, COLUMN_ID + "="+id, arrayOf(id.toString()))
            return true
        }

        return false

    }

}