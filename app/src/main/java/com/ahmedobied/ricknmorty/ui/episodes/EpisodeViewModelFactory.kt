package com.ahmedobied.ricknmorty.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmedobied.ricknmorty.data.repository.episode.EpisodeRepository

@Suppress("UNCHECKED_CAST")
class EpisodeViewModelFactory(
    private val repository: EpisodeRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpisodeViewModel(repository) as T
    }
}