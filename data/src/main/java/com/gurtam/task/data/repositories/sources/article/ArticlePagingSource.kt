package com.gurtam.task.data.repositories.sources.article

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gurtam.task.domain.models.News

class ArticlePagingSource : PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        TODO("Not yet implemented")
    }
}