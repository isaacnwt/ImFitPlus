package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.util

import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario
import java.time.LocalDate
import java.time.Period

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

    fun calculateTMB(usuario: Usuario): Double {
        return if (usuario.sexo == "Masculino") {
            66 + (13.7 * usuario.peso) + (5 * usuario.altura * 100) - (6.8 * calculateIdade(usuario.dataNascimento))
        } else {
            655 + (9.6 * usuario.peso) + (1.8 * usuario.altura * 100) - (4.7 * calculateIdade(usuario.dataNascimento))
        }
    }

    fun calculatePesoIdeal(altura: Double): Double {
        return 22 * (altura * altura)
    }

    fun calculateIngestaoAguaEmLitro(peso: Double): Double {
        return 0.35 * peso
    }

    fun calculateIdade(dataNascimentoStr: String): Int {
        val dataAtual = LocalDate.now()
        val dataNascimento = LocalDate.parse(dataNascimentoStr)
        return Period.between(dataNascimento, dataAtual).years
    }

    fun calculateFCMax(idade: Int): Int {
        return 220 - idade
    }

}