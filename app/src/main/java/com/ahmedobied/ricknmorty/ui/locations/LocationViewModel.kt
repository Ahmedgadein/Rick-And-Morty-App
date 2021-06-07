package com.ahmedobied.ricknmorty.ui.locations

import androidx.lifecycle.ViewModel
import com.ahmedobied.ricknmorty.data.repository.location.LocationRepository
import com.ahmedobied.ricknmorty.internal.lazyDeferred

class LocationViewModel(
    private val locationRepository: LocationRepository
) : ViewModel() {
    val locations by lazyDeferred {
        locationRepository.getAllLocations()
    }
}