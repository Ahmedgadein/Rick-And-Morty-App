package com.ahmedobied.ricknmorty.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.data.db.entities.LocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun insert(location:LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(locations:List<LocationEntity>)

    @Query("select * from locations_table where id == :id")
    fun getLocation(id:Int):LiveData<LocationEntity>

    @Query("select * from locations_table")
    fun getAllLocations():LiveData<List<LocationEntity>>
}