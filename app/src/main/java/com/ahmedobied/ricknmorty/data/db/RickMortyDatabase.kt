package com.ahmedobied.ricknmorty.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmedobied.ricknmorty.data.db.converters.ZonedDateTimeConverter
import com.ahmedobied.ricknmorty.data.db.dao.CharacterDao
import com.ahmedobied.ricknmorty.data.db.dao.EpisodeDao
import com.ahmedobied.ricknmorty.data.db.dao.LastFetchDao
import com.ahmedobied.ricknmorty.data.db.dao.LocationDao
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.data.db.entities.EpisodeEntity
import com.ahmedobied.ricknmorty.data.db.entities.LastFetchEntity
import com.ahmedobied.ricknmorty.data.db.entities.LocationEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [CharacterEntity::class, LocationEntity::class, EpisodeEntity::class, LastFetchEntity::class]
)
@TypeConverters(ZonedDateTimeConverter::class)
abstract class RickMortyDatabase : RoomDatabase() {
    abstract fun getLastFetchDao(): LastFetchDao
    abstract fun getCharacterDao(): CharacterDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getEpisodeDao():EpisodeDao

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