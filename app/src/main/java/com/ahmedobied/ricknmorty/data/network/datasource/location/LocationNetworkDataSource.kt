package com.ahmedobied.ricknmorty.data.network.datasource.location

import androidx.lifecycle.LiveData
import com.ahmedobied.ricknmorty.data.network.models.MultipleLocationResponse

interface LocationNetworkDataSource {
    val downloadedLocations:LiveData<MultipleLocationResponse>
    suspend fun fetchLocation(page:Int = 1)
}