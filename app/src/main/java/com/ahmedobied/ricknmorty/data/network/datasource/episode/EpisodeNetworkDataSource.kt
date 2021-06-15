package com.ahmedobied.ricknmorty.data.network.datasource.episode

import androidx.lifecycle.LiveData
import com.ahmedobied.ricknmorty.data.network.models.MultipleEpisodeResponse

interface EpisodeNetworkDataSource {
    val downloadedEpisodes:LiveData<MultipleEpisodeResponse>
    suspend fun fetchEpisodes(page:Int = 1)
}