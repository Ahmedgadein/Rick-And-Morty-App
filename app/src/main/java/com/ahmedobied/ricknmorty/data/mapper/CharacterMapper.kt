package com.ahmedobied.ricknmorty.data.mapper

import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.data.network.models.CharacterResponse

object CharacterMapper {
    fun characterResponseToEntity(characterResponse: CharacterResponse) = CharacterEntity(
        id = characterResponse.id,
        created = characterResponse.created,
//        episodesUrls = characterResponse.episode,
        gender = characterResponse.gender,
        imageUrl = characterResponse.image,
        location = LocationMapper.locationResponseToEntity(characterResponse.location),
        name = characterResponse.name,
        origin = LocationMapper.locationResponseToEntity(characterResponse.origin),
        species = characterResponse.species,
        status = characterResponse.status,
        type = characterResponse.type
    )
}