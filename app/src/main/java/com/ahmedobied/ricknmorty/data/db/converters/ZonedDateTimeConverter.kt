package com.ahmedobied.ricknmorty.data.db.converters

import androidx.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeConverter {
    @TypeConverter
    fun fromZonedDateTime(zonedDateTime: ZonedDateTime?): Long? {
        if (zonedDateTime == null) return null
        return zonedDateTime.toInstant().toEpochMilli()
    }

    @TypeConverter
    fun toZonedDateTime(time: Long?): ZonedDateTime? {
        if (time == null) return null

        val instant = Instant.ofEpochMilli(time)
        return ZonedDateTime.ofInstant(instant, ZonedDateTime.now().zone)
    }
}