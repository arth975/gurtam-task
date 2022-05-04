package com.gurtam.task.data.local.typeadapters

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime


class LocalDateTimeTypeConverter {

    @TypeConverter
    fun fromTimestamp(ts: Long?): LocalDateTime? {
        return if (ts != null) {
            LocalDateTime.ofInstant(Instant.ofEpochSecond(ts), ZoneId.systemDefault())
        } else {
            null
        }
    }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): Long? {
        return ZonedDateTime.of(dateTime, ZoneId.systemDefault()).toEpochSecond()
    }
}