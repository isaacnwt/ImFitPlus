package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.constant.Contants.DADOS_PESSOAIS
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.ActivityPesoIdealBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.DadosPessoais
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.util.CalculoUtil

class PesoIdealActivity : AppCompatActivity() {
    private val apib: ActivityPesoIdealBinding by lazy {
        ActivityPesoIdealBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apib.root)

        val dadosPessoais = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DADOS_PESSOAIS, DadosPessoais::class.java)
        } else {
            intent.getParcelableExtra(DADOS_PESSOAIS)
        }

        dadosPessoais?.let {
            val pesoIdeal = CalculoUtil.calculatePesoIdeal(it.altura)
            apib.pesoIdealTv.text = String.format("Peso Ideal: %.2f kg", pesoIdeal)
            it.pesoIdeal = pesoIdeal
        }

        apib.voltarBt.setOnClickListener {
            startActivity(Intent(this, DadosPessoaisActivity::class.java))
        }

        apib.resumoSaudeBt.setOnClickListener {
            Intent(this, ResumoSaudeActivity::class.java).apply {
                putExtra(DADOS_PESSOAIS, dadosPessoais)
                startActivity(this)
            }
        }
    }
}