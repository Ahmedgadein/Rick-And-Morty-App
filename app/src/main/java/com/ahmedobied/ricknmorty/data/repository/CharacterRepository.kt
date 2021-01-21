package com.ahmedobied.ricknmorty.data.repository

import androidx.lifecycle.LiveData
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity

interface CharacterRepository {
    suspend fun getCharacter(id:Int):LiveData<CharacterEntity>
    suspend fun getAllCharacters(page:Int = 1):LiveData<List<CharacterEntity>>
}