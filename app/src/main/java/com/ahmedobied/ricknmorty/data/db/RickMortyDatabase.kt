package com.ahmedobied.ricknmorty.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmedobied.ricknmorty.data.db.dao.CharacterDao
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity

@Database(version = 1, exportSchema = false, entities = [CharacterEntity::class])
abstract class RickMortyDatabase : RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao


    companion object {
        @Volatile
        var instance: RickMortyDatabase? = null
        private val LOCK: Any = Any()
        
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context): RickMortyDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                RickMortyDatabase::class.java,
                "RickMorty.db"
            ).build()
    }
}