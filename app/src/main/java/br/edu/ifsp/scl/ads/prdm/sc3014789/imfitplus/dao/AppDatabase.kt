package br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifsp.scl.ads.prdm.sc3014789.imfitplus.model.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}