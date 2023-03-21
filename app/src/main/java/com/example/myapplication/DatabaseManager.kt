package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)  {

    companion object {
         const val DATABASE_NAME = "mydatabase.db"
         const val DATABASE_VERSION = 1
        private const val TABLE_NAME_1 = "USUARIO"
//        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME_1 = "NOME"
        private const val COLUMN_EMAIL_1 = "EMAIL"
        private const val COLUMN_PASSWORD_1 = "SENHA"

        private const val TABLE_NAME_2 = "REPORT"
        private const val COLUMN_LATITUDE_2 = "LATITUDE"
        private const val COLUMN_LONGITUDE_2 = "LONGITUDE"
        private const val COLUMN_DESCRICAO_2 = "DESCRICAO"

    }

    override fun onCreate(p0: SQLiteDatabase?) {

       p0?.execSQL("CREATE TABLE USUARIO(\n" +
//                "\tID_USUARIO INT PRIMARY KEY AUTOINCREMENT,\n" +
                "\tNOME VARCHAR(20),\n" +
                "\tEMAIL VARCHAR(100),\n" +
                "\tSENHA VARCHAR(50)\n" +
                "\t);")

        p0?.execSQL("CREATE TABLE REPORT(\n" +
//                "\tID_USUARIO INT PRIMARY KEY AUTOINCREMENT,\n" +
                "\tLATITUDE REAL,\n" +
                "\tLONGITUDE REAL,\n" +
                "\tDESCRICAO VARCHAR(50)\n" +
                "\t);")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS USUARIO")
        p0?.execSQL("DROP TABLE IF EXISTS REPORT")


        p0?.execSQL("CREATE TABLE USUARIO(\n" +
//                "\tID_USUARIO INT PRIMARY KEY AUTOINCREMENT,\n" +
                "\tNOME VARCHAR(20),\n" +
                "\tEMAIL VARCHAR(100),\n" +
                "\tSENHA VARCHAR(20)\n" +
                "\t);")

        p0?.execSQL("CREATE TABLE REPORT(\n" +
//                "\tID_USUARIO INT PRIMARY KEY AUTOINCREMENT,\n" +
                "\tLATITUDE REAL,\n" +
                "\tLONGITUDE REAL,\n" +
                "\tDESCRICAO VARCHAR(50)\n" +
                "\t);")
    }
    fun insereUsuario(nome: String, email: String, senha:String): Any {
        var db = this.writableDatabase

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            // Algum valor está vazio, retorna -1 indicando erro
            return -1
        }

        val values = ContentValues().apply {
            put(COLUMN_NAME_1, nome)
            put(COLUMN_EMAIL_1, email)
            put(COLUMN_PASSWORD_1, senha)
        }

        val newRowId = db.insert(TABLE_NAME_1,null, values)
        db.close()

        return newRowId
        //db.insert(TABLE_NAME, null, values)
    }

    fun insereReport(lat: Double, log: Double, desc:String): Any {
        var db = this.writableDatabase


        val values = ContentValues().apply {
            put(COLUMN_LATITUDE_2, lat)
            put(COLUMN_LONGITUDE_2, log)
            put(COLUMN_DESCRICAO_2, desc)
        }

        val newRowId = db.insert(TABLE_NAME_2,null, values)
        db.close()

        return newRowId
        //db.insert(TABLE_NAME, null, values)
    }

    fun listaUsuario(): Cursor {

        var db = this.readableDatabase
        var cur = db.rawQuery("select nome,email from usuario",null)
        return cur
    }

    fun verificaLogin(username: String,password: String): Cursor {

        var db = this.readableDatabase
        var cur = db.rawQuery("SELECT * FROM USUARIO WHERE EMAIL = ? AND SENHA = ?",arrayOf(username, password))

        return cur
    }

    fun removeUsuario(){
        var db = this.writableDatabase
        db.delete("USUARIO","ID_USUARIO=1",null)
    }
}