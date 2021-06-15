package com.ahmedobied.ricknmorty.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_table")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val airDate: String,
//    val characters: List<Any>,
    val created: String,
    val episode: String,
    val name: String,
    val url: String
)