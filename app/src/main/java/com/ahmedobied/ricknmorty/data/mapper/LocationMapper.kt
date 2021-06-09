package com.ahmedobied.ricknmorty.data.mapper

import com.ahmedobied.ricknmorty.data.db.entities.LocationEntity
import com.ahmedobied.ricknmorty.data.network.models.LocationResponse

object LocationMapper {
    fun locationResponseToEntity(locationResponse: LocationResponse):LocationEntity{
    return LocationEntity(
        id = locationResponse.id,
        name = locationResponse.name,
        dimension = locationResponse.dimension,
        created = locationResponse.created,
//        residents = locationResponse.residents,
        type = locationResponse.type,
        url = locationResponse.url
    )}
}