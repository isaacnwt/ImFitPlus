package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.constant.Contants.DADOS_PESSOAIS
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.ActivityGastoCaloricoBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.util.CalculoUtil

class GastoCaloricoActivity : AppCompatActivity() {
    private val agcb: ActivityGastoCaloricoBinding by lazy {
        ActivityGastoCaloricoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(agcb.root)

        val usuario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DADOS_PESSOAIS, Usuario::class.java)
        } else {
            intent.getParcelableExtra(DADOS_PESSOAIS)
        }

        usuario?.let {
            val tmb = CalculoUtil.calculateTMB(it)
            agcb.tmbTv.text = String.format("%.2f kcal/dia", tmb)
            it.tmb = tmb
        }

        agcb.calcularPesoIdealBt.setOnClickListener {
            Intent(this, PesoIdealActivity::class.java).apply {
                putExtra(DADOS_PESSOAIS, usuario)
                startActivity(this)
            }
        }
        agcb.voltarBt.setOnClickListener {
            startActivity(Intent(this, DadosPessoaisActivity::class.java))
        }
    }
}