package com.ahmedobied.ricknmorty.ui.episodes

import androidx.lifecycle.ViewModel
import com.ahmedobied.ricknmorty.data.repository.episode.EpisodeRepository
import com.ahmedobied.ricknmorty.internal.lazyDeferred

class EpisodeViewModel(
    episodeRepository: EpisodeRepository
) : ViewModel() {
    val episodes by lazyDeferred {
        episodeRepository.getAllEpisodes()
    }
}