package com.ahmedobied.ricknmorty.data.repository.location

import androidx.lifecycle.LiveData
import com.ahmedobied.ricknmorty.data.db.entities.LocationEntity

interface LocationRepository {
    suspend fun getAllLocations():LiveData<List<LocationEntity>>
}