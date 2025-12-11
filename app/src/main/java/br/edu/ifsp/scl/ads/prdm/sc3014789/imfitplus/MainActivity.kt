package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.controller.UsuarioController
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val usuarioController: UsuarioController by lazy {
        UsuarioController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        usuarioController.hasUsuarios { hasUsuarios ->
            amb.comecarBt.setOnClickListener {
                if (hasUsuarios) {
                    val intent = Intent(this, HistoricoCalculosActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, DadosPessoaisActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}