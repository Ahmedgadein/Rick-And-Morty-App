package com.ahmedobied.ricknmorty.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class LocationEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val created: String,
    val dimension: String,
    val name: String,
//    val residents: List<String>,
    val type: String,
    val url: String
)