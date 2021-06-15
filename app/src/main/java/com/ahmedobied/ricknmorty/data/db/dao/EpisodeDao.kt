package com.ahmedobied.ricknmorty.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedobied.ricknmorty.data.db.entities.EpisodeEntity

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(episode: EpisodeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(episodes: List<EpisodeEntity>)

    @Query("select * from episodes_table where id == :id")
    fun getEpisode(id: Int): LiveData<EpisodeEntity>

    @Query("select * from episodes_table")
    fun getAllEpisodes(): LiveData<List<EpisodeEntity>>
}