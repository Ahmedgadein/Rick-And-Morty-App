package com.ahmedobied.ricknmorty.data.network.datasource.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmedobied.ricknmorty.data.network.common.RickAndMortyApiService
import com.ahmedobied.ricknmorty.data.network.models.MultipleLocationResponse
import com.ahmedobied.ricknmorty.internal.NoNetworkException

class LocationNetworkDataSourceImpl(
    private val networkService: RickAndMortyApiService
) : LocationNetworkDataSource {
    private val _downloadedLocations: MutableLiveData<MultipleLocationResponse> = MutableLiveData()

    override val downloadedLocations: LiveData<MultipleLocationResponse>
        get() = _downloadedLocations

    override suspend fun fetchLocation(page: Int?) {
        try {
            val result = networkService.getLocations(page)
            _downloadedLocations.postValue(result)
        } catch (exception: NoNetworkException) {
            Log.e("Connectivity", "No Network Connection", exception)
        }
    }
}