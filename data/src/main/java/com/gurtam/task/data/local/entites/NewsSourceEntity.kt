package com.gurtam.task.data.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newsSources")
data class NewsSourceEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String?,
    val description: String?,
    val url: String?,
    val category: String?,
    val language: String?,
    val country: String?
)
