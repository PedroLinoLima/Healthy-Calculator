package com.example.projecteixo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.projecteixo.R
import com.example.projecteixo.utils.getDicaDoDiaNcd

class ResultadoNcdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_ncd)

        supportActionBar!!.title = "Aqui está o seu resultado"

        val textViewNcd: TextView = findViewById(R.id.text_view_ncd)
        val textViewDica: TextView = findViewById(R.id.text_view_dica)

        val ncd = intent.getDoubleExtra("ncd", 0.0)

        textViewNcd.text = ncd.toInt().toString()
        textViewDica.text =
            getDicaDoDiaNcd()

    }
}