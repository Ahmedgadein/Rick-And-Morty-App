package com.ahmedobied.ricknmorty.data.mapper

import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.data.db.entities.EpisodeEntity
import com.ahmedobied.ricknmorty.data.network.models.EpisodeResponse

object EpisodeMapper {
    fun episodeResponseToEntity(response:EpisodeResponse) = EpisodeEntity(
        id = response.id,
        created = response.created,
        airDate = response.airDate,
        name = response.name,
        url = response.url,
        episode = response.episode
    )
}