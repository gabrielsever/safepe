package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class CadastroActivity() : AppCompatActivity()  {

    val database = DatabaseManager(this)


    private lateinit var edtNome: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtSenha: EditText
    private lateinit var btnCadastrar: Button



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtSenha = findViewById<EditText>(R.id.edtSenha)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)



        btnCadastrar.setOnClickListener {
            val nome = edtNome.text.toString()
            val email = edtEmail.text.toString()
            val senha = edtSenha.text.toString()
            database.insereUsuario(nome, email, senha)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }




}


