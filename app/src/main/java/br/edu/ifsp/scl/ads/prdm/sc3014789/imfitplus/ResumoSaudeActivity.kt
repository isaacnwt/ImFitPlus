package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.constant.Contants.DADOS_PESSOAIS
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.ActivityResumoSaudeBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.util.CalculoUtil

class ResumoSaudeActivity : AppCompatActivity() {
    private val arsb: ActivityResumoSaudeBinding by lazy {
        ActivityResumoSaudeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(arsb.root)

        val usuario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DADOS_PESSOAIS, Usuario::class.java)
        } else {
            intent.getParcelableExtra(DADOS_PESSOAIS)
        }

        usuario?.let {
            with(arsb) {
                val recomendacaoAgua = CalculoUtil.calculateIngestaoAguaEmLitro(it.peso)
                nomeTv.text = "Nome: ${it.nome}"
                imcTv.text = String.format("IMC: %.2f kg/m2", it.imc)
                categoriaImcTv.text = "Categoria: ${it.categoriaImc}"
                pesoIdealTv.text = String.format("Peso Ideal: %.2f kg", it.pesoIdeal)
                gastoCaloricoTv.text = String.format("Gasto Calórico: %.2f kcal/dia", it.tmb)
                recomendacaoAguaTv.text = String.format("Recomendação de Ingestão de Água: %.2f L", recomendacaoAgua)
            }
        }

        arsb.verCalculosBt.setOnClickListener {
            val intent = Intent(this, HistoricoCalculosActivity::class.java)
            startActivity(intent)
        }
    }
}