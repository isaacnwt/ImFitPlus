package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.constant.Contants.DADOS_PESSOAIS
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.ActivityPesoIdealBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.util.CalculoUtil

class PesoIdealActivity : AppCompatActivity() {
    private val apib: ActivityPesoIdealBinding by lazy {
        ActivityPesoIdealBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apib.root)

        val usuario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DADOS_PESSOAIS, Usuario::class.java)
        } else {
            intent.getParcelableExtra(DADOS_PESSOAIS)
        }

        usuario?.let {
            val pesoIdeal = CalculoUtil.calculatePesoIdeal(it.altura)
            apib.pesoIdealTv.text = String.format("Peso Ideal: %.2f kg", pesoIdeal)
            it.pesoIdeal = pesoIdeal
        }

        apib.voltarBt.setOnClickListener {
            startActivity(Intent(this, DadosPessoaisActivity::class.java))
        }

        apib.resumoSaudeBt.setOnClickListener {
            Intent(this, ResumoSaudeActivity::class.java).apply {
                putExtra(DADOS_PESSOAIS, usuario)
                startActivity(this)
            }
        }
    }
}