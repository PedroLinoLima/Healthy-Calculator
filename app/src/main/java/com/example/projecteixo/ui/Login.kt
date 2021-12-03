package com.example.projecteixo.ui

import com.example.projecteixo.R

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.projecteixo.dao.UsuarioDao

class Login : AppCompatActivity() {

    lateinit var editUser: EditText
    lateinit var editPassword: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.title = "Login"

        editUser = findViewById(R.id.et_email)
        editPassword = findViewById(R.id.et_senha)
        btnLogin = findViewById(R.id.btn_login)

        btnLogin.setOnClickListener {
            login()
        }

    }

    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun login() {

        val user = editUser.text.toString()
        val pass = editPassword.text.toString()

        val dao = UsuarioDao(this, null)

        val autenticado = dao.autenticar(user, pass)

        if (autenticado) {
            abrirDashBoard()
        }

    }
}