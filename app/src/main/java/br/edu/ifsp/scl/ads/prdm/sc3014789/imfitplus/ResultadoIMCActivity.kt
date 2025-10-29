package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.constant.Contants.DADOS_PESSOAIS
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.ActivityResultadoImcBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.DadosPessoais
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.util.CalculoUtil

class ResultadoIMCActivity : AppCompatActivity() {

    private val arib: ActivityResultadoImcBinding by lazy {
        ActivityResultadoImcBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(arib.root)

        val dadosPessoais = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DADOS_PESSOAIS, DadosPessoais::class.java)
        } else {
            intent.getParcelableExtra(DADOS_PESSOAIS)
        }
        dadosPessoais?.let {
            with(arib) {
                nomeTv.text = it.nome
                imcTv.text = String.format("IMC: %.2f", CalculoUtil.calculateIMC(it.peso, it.altura))
                categoriaTv.text = "Categoria: ${CalculoUtil.getFormattedIMC(it.peso, it.altura)}"
            }
        }



    }
}