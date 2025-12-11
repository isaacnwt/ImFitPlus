package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.controller

import android.content.Context
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.dao.AppDatabase
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioController(context: Context) {

    private val usuarioDao = AppDatabase.getInstance(context).usuarioDao()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun insertUsuario(usuario: Usuario, onInserted: (Usuario) -> Unit) {
        coroutineScope.launch {
            val id = usuarioDao.createUsuario(usuario)
            usuario.id = id.toInt()

            withContext(Dispatchers.Main) {
                onInserted(usuario)
            }
        }
    }

    fun updateUsuario(usuario: Usuario) {
        coroutineScope.launch {
            usuarioDao.updateUsuario(usuario)
        }
    }
}