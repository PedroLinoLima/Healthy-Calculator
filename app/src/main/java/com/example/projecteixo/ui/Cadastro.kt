package com.example.projecteixo.ui

import com.example.projecteixo.R
import com.example.projecteixo.model.Usuario

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.projecteixo.dao.UsuarioDao
import java.util.*

const val CODE_IMAGE = 100

class Cadastro : AppCompatActivity() {

    lateinit var tvTrocarFoto: TextView
    lateinit var etDataNascimento: EditText
    lateinit var etEmail: EditText
    lateinit var etSenha: EditText
    lateinit var etNome: EditText
    lateinit var etProfissao: EditText
    lateinit var etAltura: EditText
    lateinit var radioFeminino: RadioButton
    lateinit var ivTrocarFoto: ImageView

    var imageBitmap: Bitmap? = null
    lateinit var imgProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar!!.title = "Cadastro"
        supportActionBar!!.subtitle = "Cadastre o seus dados"

        imgProfile = findViewById(R.id.img_profile)
        tvTrocarFoto = findViewById(R.id.tv_trocar_foto)
        etDataNascimento = findViewById(R.id.et_data_nascimento)
        etEmail = findViewById(R.id.et_email)
        etSenha = findViewById(R.id.et_senha)
        etNome = findViewById(R.id.et_nome)
        etProfissao = findViewById(R.id.et_profissao)
        etAltura = findViewById(R.id.et_altura)
        radioFeminino = findViewById(R.id.radio_feminino)
        ivTrocarFoto = findViewById(R.id.img_profile)

        // Detectar o click no texto "Trocar foto"
        tvTrocarFoto.setOnClickListener {
            abrirGaleria()
        }

        ivTrocarFoto.setOnClickListener {
            abrirGaleria()
        }

        // Criar um calendário
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        // Abrir um componente DatePickerDialog
        etDataNascimento.setOnClickListener {
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                    var diaZero = "$_dia"
                    var mesZero = "$_mes"

                    if (_dia < 10) {
                        diaZero = "0$_dia"
                    }

                    if (_mes < 10) {
                        mesZero = "${_mes + 1}"
                    }
                    etDataNascimento.setText("$diaZero/$mesZero/$_ano")
                }, ano, mes, dia
            )
            dpd.show()
        }

    }

    private fun salvar() {
        val usuario = Usuario(
            0,
            etEmail.text.toString(),
            etSenha.text.toString(),
            etNome.text.toString(),
            etProfissao.text.toString(),
            etAltura.text.toString().toDouble(),
            etDataNascimento.text.toString(),
            if (radioFeminino.isChecked) 'F' else 'M',
            imageBitmap
        )

        val dao = UsuarioDao(this, usuario)
        dao.gravar()

        Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()

        finish()
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    private fun cancelarTela() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_salvar -> {
                salvar()
                return true
            }

            R.id.menu_cancelar -> {
                cancelarTela()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun abrirGaleria() {

        // Chamando a galeria de imagens
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // Definir qual o tipo de conteúdo deverá ser obtido
        intent.type = "image/*"

        // Iniciar a Activity, mas neste caso nós queremos que
        // esta Activity retorne algo pra gente, a imagem
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Escolha uma foto"
            ),
            CODE_IMAGE
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == -1) {

            // Recuperar a imagem no stream
            val stream = contentResolver.openInputStream(data!!.data!!)

            // Transformar o stream em um BitMap
            imageBitmap = BitmapFactory.decodeStream(stream)

            // Colocar a imagem no ImageView
            imgProfile.setImageBitmap(imageBitmap)
        }

    }
}

