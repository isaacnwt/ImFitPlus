package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.util

object CalculoUtil {
    fun getFormattedIMC(peso: Double, altura: Double): String {
        val imc = calculateIMC(peso, altura)
        if (imc < 18.5) return "Abaixo do peso"
        else if (imc < 24.9) return "Normal"
        else if (imc < 29.9) return "Sobrepeso"
        else return "Obesidade"
    }

    fun calculateIMC(peso: Double, altura: Double): Double {
        return peso / (altura * altura)
    }
}