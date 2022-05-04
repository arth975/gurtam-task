package com.gurtam.task.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsSourceUI(
    val id: String,
    val name: String?,
    val description: String?
) : Parcelable
