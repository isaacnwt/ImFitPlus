package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var nome: String = "",
    var dataNascimento: String = "",
    var sexo: String = "",
    var altura: Double = 0.0,
    var peso: Double = 0.0,
    var nivelAtividade: String = "",
    var imc: Double? = 0.0,
    var categoriaImc: String? = "",
    var pesoIdeal: Double? = 0.0,
    var tmb: Double? = 0.0,
    var dataCalculo: Long = System.currentTimeMillis(),
    var frequenciaCardiacaMax: Int? = 0,
): Parcelable
