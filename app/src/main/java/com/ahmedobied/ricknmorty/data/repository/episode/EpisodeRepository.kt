package com.ahmedobied.ricknmorty.data.repository.episode

import androidx.lifecycle.LiveData
import com.ahmedobied.ricknmorty.data.db.entities.EpisodeEntity

interface EpisodeRepository {
    suspend fun getAllEpisodes():LiveData<List<EpisodeEntity>>
}