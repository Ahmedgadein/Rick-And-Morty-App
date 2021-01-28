package com.ahmedobied.ricknmorty.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

const val LAST_FETCH_ID = 0

@Entity(tableName = "last_fetch_table")
data class LastFetchEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = LAST_FETCH_ID,
    val lastFetchTime: ZonedDateTime,
    val nextPage: Int
)