package com.ahmedobied.ricknmorty.data.repository.episode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ahmedobied.ricknmorty.data.db.dao.EpisodeDao
import com.ahmedobied.ricknmorty.data.db.entities.EpisodeEntity
import com.ahmedobied.ricknmorty.data.mapper.EpisodeMapper
import com.ahmedobied.ricknmorty.data.network.datasource.episode.EpisodeNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.models.MultipleEpisodeResponse
import kotlinx.coroutines.*

class EpisodeRepositoryImpl(
    private val episodeNetworkDataSource: EpisodeNetworkDataSource,
    private val episodeDao: EpisodeDao
) : EpisodeRepository {

    init {
        episodeNetworkDataSource.downloadedEpisodes.observeForever(Observer {
            if (it != null)
                persistEpisodes(it)
        })
    }

    private fun persistEpisodes(episodesResponse: MultipleEpisodeResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val episodes = episodesResponse.episodes.map {
                EpisodeMapper.episodeResponseToEntity(it)
            }
            episodeDao.insert(episodes)
        }
    }

    override suspend fun getAllEpisodes(): LiveData<List<EpisodeEntity>> {
        return withContext(Dispatchers.IO) {
            initEpisodes()
            return@withContext episodeDao.getAllEpisodes()
        }
    }

    private suspend fun initEpisodes() {
        if (shouldFetch())
            fetchEpisodes()
    }

    private fun shouldFetch(): Boolean {
        return true
    }

    private suspend fun fetchEpisodes(page: Int = 1) {
        GlobalScope.launch(Dispatchers.IO) {
            episodeNetworkDataSource.fetchEpisodes(page)
        }
    }
}