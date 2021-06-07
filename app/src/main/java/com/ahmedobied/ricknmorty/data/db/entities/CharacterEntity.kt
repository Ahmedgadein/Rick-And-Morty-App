package com.ahmedobied.ricknmorty.data.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmedobied.ricknmorty.data.network.models.LocationResponse

@Entity(tableName = "characters_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val created: String,
//    val episodesUrls: List<String>,
    val gender: String,
    val imageUrl: String,
    @Embedded(prefix = "location_")
    val location: LocationEntity,
    val name: String,
    @Embedded(prefix = "origin_")
    val origin: LocationEntity,
    val species: String,
    val status: String,
    val type: String
)