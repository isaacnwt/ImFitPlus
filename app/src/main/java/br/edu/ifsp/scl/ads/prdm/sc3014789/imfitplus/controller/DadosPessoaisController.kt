package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.controller

import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.DadosPessoaisActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.dao.AppDatabase
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DadosPessoaisController(dadosPessoaisActivity: DadosPessoaisActivity) {

    private val usuarioDao = AppDatabase.getInstance(dadosPessoaisActivity).usuarioDao()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun insertUsuario(usuario: Usuario) {
        coroutineScope.launch {
            usuarioDao.createUsuario(usuario)
        }
    }
}