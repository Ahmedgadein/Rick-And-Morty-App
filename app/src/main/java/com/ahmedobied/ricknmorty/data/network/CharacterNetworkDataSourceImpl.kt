package com.ahmedobied.ricknmorty.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmedobied.ricknmorty.data.network.common.RickAndMortyApiService
import com.ahmedobied.ricknmorty.data.network.models.MultipleCharacterResponse
import com.ahmedobied.ricknmorty.internal.NoNetworkException

class CharacterNetworkDataSourceImpl(
    private val networkService: RickAndMortyApiService
) : CharacterNetworkDataSource {

    private val _characters: MutableLiveData<MultipleCharacterResponse> =
        MutableLiveData()

    override val downloadedCharacters: LiveData<MultipleCharacterResponse>
        get() = _characters

    override suspend fun fetchCharacters(page: Int) {
        try {
            val result = networkService.getCharacters(page)
            _characters.postValue(result)
        } catch (exception: NoNetworkException) {
            Log.e("Connectivity", "No Internet Connection: ", exception)
        }
    }
}