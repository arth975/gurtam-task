package com.gurtam.task.models.mappers

import com.gurtam.task.domain.models.NewsSource
import com.gurtam.task.models.NewsSourceUI

fun NewsSource.toUI() = NewsSourceUI(
    id = this.id,
    name = this.name,
    description = this.description
)