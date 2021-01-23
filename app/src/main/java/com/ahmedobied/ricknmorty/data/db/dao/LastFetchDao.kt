package com.ahmedobied.ricknmorty.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedobied.ricknmorty.data.db.entities.LAST_FETCH_ID
import com.ahmedobied.ricknmorty.data.db.entities.LastFetchEntity

@Dao
interface LastFetchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lastFetch: LastFetchEntity)

    @Query("select * from last_fetch_table where id = $LAST_FETCH_ID")
    fun getLastFetch(): LastFetchEntity
}