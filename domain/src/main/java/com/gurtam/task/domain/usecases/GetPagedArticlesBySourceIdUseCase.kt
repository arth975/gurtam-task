package com.gurtam.task.domain.usecases

import com.gurtam.task.domain.repositories.ArticleRepository
import kotlinx.coroutines.flow.Flow

class GetPagedArticlesBySourceIdUseCase(private val articleRepository: ArticleRepository) {

    /**
     * Здесь явно не указан тип Flow<>, так как из слойа data мы получаем объект типа PagingData,
     * который является частью анроидовской библиотеки PagingLibrary, а по принципам Clean
     * Architecture domain слой не должен зависить от какого либо framework-а. Все необходимые
     * преобразование происходят в слойе presentation - в нашем случае в ArticlesListViewModel.
     * */
    suspend operator fun invoke(sourceId: String): Flow<*> {
        return articleRepository.getArticlesBySourceId(sourceId)
    }

}