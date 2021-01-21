package com.ahmedobied.ricknmorty.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character:CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characters: List<CharacterEntity>)

    @Query("select * from characters_table where id == :id ")
    fun getCharacter(id:Int): LiveData<CharacterEntity>

    @Query("select * from characters_table")
    fun getAllCharacters():LiveData<List<CharacterEntity>>
}