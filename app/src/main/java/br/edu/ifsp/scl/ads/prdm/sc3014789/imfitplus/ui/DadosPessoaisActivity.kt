package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.constant.Contants.DADOS_PESSOAIS
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.controller.UsuarioController
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.ActivityDadosPessoaisBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario

class DadosPessoaisActivity : AppCompatActivity() {
    private val adpb: ActivityDadosPessoaisBinding by lazy {
        ActivityDadosPessoaisBinding.inflate(layoutInflater)
    }

    private val usuarioController: UsuarioController by lazy {
        UsuarioController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(adpb.root)

        with(adpb) {
            calcularBt.setOnClickListener {
                val nome = getNomeValue()
                val idade = getIdadeValue()
                val altura = getConvertedValue(alturaEt.text.toString(), "Altura")
                val peso = getConvertedValue(pesoEt.text.toString(), "Peso")
                if (nome == null || idade == null || altura == null || peso == null)
                    return@setOnClickListener
                Usuario(
                    nome = nome,
                    idade = idade,
                    sexo = getSelectedSexo(),
                    altura = altura,
                    peso = peso,
                    nivelAtividade = nivelAtividadeSp.selectedItem.toString(),
                ).let { usuario ->
                    usuarioController.insertUsuario(usuario) { usuarioComId ->
                        Intent(this@DadosPessoaisActivity, ResultadoIMCActivity::class.java).apply {
                            putExtra(DADOS_PESSOAIS, usuarioComId)
                            startActivity(this)
                        }
                    }
                }
            }
        }
    }

    private fun getNomeValue(): String? {
        val nome = adpb.nomeEt.text.toString()
        if (nome.length < 3) {
            Toast.makeText(this, "Nome deve conter no mínimo 3 caracteres!", LENGTH_SHORT).show()
            return null
        }
        return nome
    }

    private fun getIdadeValue(): Int? {
        val idadeStr = adpb.idadeEt.text.toString().trim()

        if (idadeStr.isEmpty()) {
            Toast.makeText(this, "Idade não pode ser vazia!", LENGTH_SHORT).show()
            return null
        }

        val idade = idadeStr.toIntOrNull()
        if (idade == null) {
            Toast.makeText(this, "Idade deve conter apenas números!", LENGTH_SHORT).show()
            return null
        }

        if (idade <= 0) {
            Toast.makeText(this, "Idade deve ser maior que 0!", LENGTH_SHORT).show()
            return null
        }
        return idade
    }

    private fun getConvertedValue(value: String, fieldName: String): Double? {
        val strValue = value.trim()

        if (strValue.isEmpty()) {
            Toast.makeText(this, "$fieldName não pode ser vazia!", LENGTH_SHORT).show()
            return null
        }

        val convertedValue = strValue.toDoubleOrNull()
        if (convertedValue == null) {
            Toast.makeText(this, "$fieldName deve conter apenas números!", LENGTH_SHORT).show()
            return null
        }

        if (convertedValue <= 0) {
            Toast.makeText(this, "$fieldName deve ser maior que 0!", LENGTH_SHORT).show()
            return null
        }
        return convertedValue
    }

    private fun getSelectedSexo(): String = with(adpb) {
        return when {
            masculinoRb.isChecked -> masculinoRb.text.toString()
            femininoRb.isChecked -> femininoRb.text.toString()
            else -> ""
        }
    }
}