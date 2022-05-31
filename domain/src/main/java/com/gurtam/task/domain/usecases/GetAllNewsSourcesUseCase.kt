package com.gurtam.task.domain.usecases

import com.gurtam.task.domain.models.NewsSource
import com.gurtam.task.domain.models.ResultOf
import com.gurtam.task.domain.repositories.NewsSourceRepository
import kotlinx.coroutines.flow.Flow

class GetAllNewsSourcesUseCase(private val newsSourceRepo: NewsSourceRepository) {

    suspend operator fun invoke(): Flow<ResultOf<List<NewsSource>>> = newsSourceRepo.getAll()
}