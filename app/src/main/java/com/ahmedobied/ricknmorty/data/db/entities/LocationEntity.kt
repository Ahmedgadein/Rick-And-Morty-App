package com.ahmedobied.ricknmorty.data.db.entities

import androidx.room.Entity

@Entity(tableName = "locations_table")
data class LocationEntity(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)