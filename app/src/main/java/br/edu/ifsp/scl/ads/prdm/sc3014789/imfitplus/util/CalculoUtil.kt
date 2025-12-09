package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.util

import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario

object CalculoUtil {
    fun getCategoriaIMC(peso: Double, altura: Double): String {
        val imc = calculateIMC(peso, altura)
        return if (imc < 18.5) "Abaixo do peso"
        else if (imc < 24.9) "Normal"
        else if (imc < 29.9) "Sobrepeso"
        else "Obesidade"
    }

    fun calculateIMC(peso: Double, altura: Double): Double {
        return peso / (altura * altura)
    }

    fun calculateTMB(dp: Usuario): Double {
        return if (dp.sexo == "Masculino") {
            66 + (13.7 * dp.peso) + (5 * dp.altura * 100) - (6.8 * dp.idade)
        } else {
            655 + (9.6 * dp.peso) + (1.8 * dp.altura * 100) - (4.7 * dp.idade)
        }
    }

    fun calculatePesoIdeal(altura: Double): Double {
        return 22 * (altura * altura)
    }

    fun calculateIngestaoAguaEmLitro(peso: Double): Double {
        return 0.35 * peso
    }
}