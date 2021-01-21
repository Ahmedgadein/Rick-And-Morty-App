package com.ahmedobied.ricknmorty.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmedobied.ricknmorty.data.network.models.CharacterResponse
import com.ahmedobied.ricknmorty.data.network.models.MultipleCharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterNetworkDataSourceImpl(
    private val networkService: RickAndMortyApiService
) : CharacterNetworkDataSource {

    private val _characters: MutableLiveData<MultipleCharacterResponse> =
        MutableLiveData()

    override val downloadedCharacters: LiveData<MultipleCharacterResponse>
        get() = _characters

    override suspend fun fetchCharacters(page: Int) {
        val result = networkService.getCharacters(page)
        _characters.postValue(result)
    }
}