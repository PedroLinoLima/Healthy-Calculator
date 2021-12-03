package com.example.projecteixo.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.projecteixo.R
import com.example.projecteixo.utils.calcularImc
import com.example.projecteixo.utils.calcularNcd
import com.example.projecteixo.utils.converterBase64ParaBitmap
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {

    lateinit var cardImc: CardView
    lateinit var cardNcd: CardView
    lateinit var tvProfileName: TextView
    lateinit var tvProfileOcupation: TextView
    lateinit var tvAge: TextView
    lateinit var ivProfile: ImageView

    var id = 0
    var nome = ""
    var profissao = ""
    var idade = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        cardImc = findViewById(R.id.card_imc)
        cardNcd = findViewById(R.id.card_ncd)
        tvProfileName = findViewById(R.id.tv_profile_name)
        tvProfileOcupation = findViewById(R.id.tv_profile_occupation)
        tvAge = findViewById(R.id.tv_age)
        ivProfile = findViewById(R.id.iv_profile)

        preencherDashBoard()

        cardImc.setOnClickListener {
            atualizarBioImc()
        }

        cardNcd.setOnClickListener {
            atualizarBioNcd()
        }

    }

    private fun atualizarBioImc() {
        val intent = Intent(this, ImcActivity::class.java)
        intent.putExtra("id_usuario", id)
        startActivity(intent)
    }

    private fun atualizarBioNcd() {
        val intent = Intent(this, NcdActivity::class.java)
        intent.putExtra("id_usuario", id)
        startActivity(intent)
    }

    private fun preencherDashBoard() {
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        nome = dados.getString("nome", "")!!
        profissao = dados.getString("profissao", "")!!
        idade = dados.getString("idade", "")!!


        tvProfileName.text = nome
        tvProfileOcupation.text = profissao
        tvAge.text = idade

        val imagemBase64 = dados.getString("foto", "")
        val imagemBitmap = converterBase64ParaBitmap(imagemBase64)

        id = dados.getInt("id_usuario", 0)

        ivProfile.setImageBitmap(imagemBitmap)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dash_board, menu);
        return true;
    }

    private fun sair() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_sair -> {
                sair()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        preencherDashBoard()
    }
}