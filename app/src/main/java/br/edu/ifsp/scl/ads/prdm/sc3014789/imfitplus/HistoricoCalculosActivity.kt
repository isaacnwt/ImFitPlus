package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.adapter.UsuarioAdapter
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.controller.UsuarioController
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.ActivityHistoricoCalculosBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario

class HistoricoCalculosActivity : AppCompatActivity() {
    private val ahcb: ActivityHistoricoCalculosBinding by lazy {
        ActivityHistoricoCalculosBinding.inflate(layoutInflater)
    }

    private val usuarios: MutableList<Usuario> = mutableListOf()

    private val usuarioAdapter: UsuarioAdapter by lazy {
        UsuarioAdapter(this, usuarios)
    }

    private val usuarioController: UsuarioController by lazy {
        UsuarioController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ahcb.root)

        ahcb.usuariosLv.adapter = usuarioAdapter

        loadUsuarios()

        ahcb.novoCalculoBt.setOnClickListener {
            val intent = Intent(this, DadosPessoaisActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadUsuarios() {
        usuarioController.getUsuarios { lista ->
            usuarioAdapter.clear()
            usuarioAdapter.addAll(lista)
            usuarioAdapter.notifyDataSetChanged()
        }
    }
}