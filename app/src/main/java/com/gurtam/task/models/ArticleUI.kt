package com.gurtam.task.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ArticleUI(
    val title: String?,
    val description: String?,
    val publishDate: LocalDateTime?,
    val imageUrl: String?,
    val url: String?
) : Parcelable
