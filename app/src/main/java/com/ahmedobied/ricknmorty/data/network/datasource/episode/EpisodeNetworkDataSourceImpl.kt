package com.ahmedobied.ricknmorty.data.network.datasource.episode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmedobied.ricknmorty.data.network.common.RickAndMortyApiService
import com.ahmedobied.ricknmorty.data.network.models.MultipleEpisodeResponse
import com.ahmedobied.ricknmorty.internal.NoNetworkException

class EpisodeNetworkDataSourceImpl(
    private val networkService: RickAndMortyApiService
) : EpisodeNetworkDataSource {
    private val _downloadedEpisode: MutableLiveData<MultipleEpisodeResponse> = MutableLiveData()

    override val downloadedEpisodes: LiveData<MultipleEpisodeResponse>
        get() = _downloadedEpisode

    override suspend fun fetchEpisodes(page: Int) {
        try {
            val result = networkService.getEpisodes(page)
            _downloadedEpisode.postValue(result)
        } catch (exception: NoNetworkException) {
            Log.e("Connectivity", "No Internet Connection: ", exception)
        }
    }
}