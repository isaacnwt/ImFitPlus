package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    fun createUsuario(usuario: Usuario): Long

    @Update
    fun updateUsuario(usuario: Usuario): Int

    @Query("SELECT * FROM Usuario ORDER BY dataCalculo DESC")
    fun getUsuarios(): MutableList<Usuario>

    @Query("SELECT COUNT(*) FROM Usuario")
    fun getCount(): Int
}