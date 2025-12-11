package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.databinding.TileUsuarioBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UsuarioAdapter(
    context: Context,
    private val usuarios: MutableList<Usuario>
): ArrayAdapter<Usuario>(
    context,
    R.layout.tile_usuario,
    usuarios
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val usuario = usuarios[position]

        var usuarioTileView = convertView
        if (usuarioTileView == null) {
            TileUsuarioBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            ).apply {
                usuarioTileView = root
                val tileUsuarioHolder = TileUsuarioHolder(nomeTv, imcTv, dataTv)
                usuarioTileView.tag = tileUsuarioHolder
            }
        }

        val tlViewHolder = usuarioTileView?.tag as TileUsuarioHolder
        tlViewHolder.nomeTv.text = usuario.nome
        tlViewHolder.imcTv.text = String.format("IMC: %.2f kg/m2", usuario.imc)
        tlViewHolder.dataTv.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            .format(Date(usuario.dataCalculo))
        return usuarioTileView!!
    }

    private data class TileUsuarioHolder(val nomeTv: TextView, val imcTv: TextView, val dataTv: TextView)
}