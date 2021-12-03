package com.example.projecteixo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.projecteixo.R

class MainActivity : AppCompatActivity() {

    lateinit var tvLogin: TextView
    lateinit var tvCadastro: TextView

    lateinit var ivLogin: ImageView
    lateinit var ivCadastro: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        tvLogin = findViewById(R.id.tv_login)
        ivLogin = findViewById(R.id.iv_login)
        tvCadastro = findViewById(R.id.tv_cadastro)
        ivCadastro = findViewById(R.id.iv_cadastro)

        tvLogin.setOnClickListener {
            IrParaTelaLogin()
        }
        ivLogin.setOnClickListener {
            IrParaTelaLogin()
        }
        tvCadastro.setOnClickListener {
            IrParaTelaCadastro()
        }
        ivCadastro.setOnClickListener {
            IrParaTelaCadastro()
        }

    }

    private fun IrParaTelaLogin() {

        val login = Intent(this, Login::class.java)
        startActivity(login)

    }
    private fun IrParaTelaCadastro() {

        val cadastro = Intent(this, Cadastro::class.java)
        startActivity(cadastro)

    }

}