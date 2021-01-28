package com.ahmedobied.ricknmorty.data.repository

import androidx.lifecycle.LiveData
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity

interface CharacterRepository {
    suspend fun getAllCharacters():LiveData<List<CharacterEntity>>
    suspend fun fetchNextPage()
}