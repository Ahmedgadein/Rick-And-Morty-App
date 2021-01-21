package com.ahmedobied.ricknmorty.data.network

import androidx.lifecycle.LiveData
import com.ahmedobied.ricknmorty.data.network.models.CharacterResponse
import com.ahmedobied.ricknmorty.data.network.models.MultipleCharacterResponse

interface CharacterNetworkDataSource {
    val downloadedCharacters: LiveData<MultipleCharacterResponse>
    suspend fun fetchCharacters(page: Int = 1)
}