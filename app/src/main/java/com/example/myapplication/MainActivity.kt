package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val db = DatabaseManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val cadastroButton = findViewById<Button>(R.id.cadastroButton)


        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val cursor = db.verificaLogin(username,password)


            if (cursor.moveToFirst()) {
                // Login bem-sucedido
                Toast.makeText(this, "Logado com sucesso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
                }
             else {
                // Login falhou
                Toast.makeText(this, "Não foi possível fazer o login. Usuário ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        cadastroButton.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

    }

}