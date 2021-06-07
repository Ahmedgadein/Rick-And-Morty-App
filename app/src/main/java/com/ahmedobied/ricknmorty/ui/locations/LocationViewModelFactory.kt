package com.ahmedobied.ricknmorty.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmedobied.ricknmorty.data.repository.location.LocationRepository

@Suppress("UNCHECKED_CAST")
class LocationViewModelFactory(
    private val locationRepository: LocationRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationViewModel(locationRepository) as T
    }
}