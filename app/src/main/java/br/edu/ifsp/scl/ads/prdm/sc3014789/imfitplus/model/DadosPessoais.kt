package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DadosPessoais(
    var nome: String = "",
    var idade: Int = 0,
    var sexo: String = "",
    var altura: Double = 0.0,
    var peso: Double = 0.0,
    var nivelAtividade: String = "",
): Parcelable
