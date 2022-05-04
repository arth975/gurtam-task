package com.gurtam.task.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.gurtam.task.domain.models.Article
import com.gurtam.task.domain.usecases.GetPagedArticlesBySourceIdUseCase
import com.gurtam.task.models.ArticleUI
import com.gurtam.task.models.mappers.toUI
import com.gurtam.task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesListViewModel @Inject constructor(
    private val getPagedArticlesBySourceIdUseCase: GetPagedArticlesBySourceIdUseCase
) : ViewModel() {

    private val mArticlesPagingDataLiveData =
        MutableLiveData<Resource<PagingData<ArticleUI>>>()
    val articlePagingDataLiveData: LiveData<Resource<PagingData<ArticleUI>>> =
        mArticlesPagingDataLiveData

    fun fetchArticles(sourceId: String) {
        viewModelScope.launch {
            getPagedArticlesBySourceIdUseCase(sourceId).collectLatest { pagingData ->
                mArticlesPagingDataLiveData.value = try {
                    val data = (pagingData as PagingData<*>)
                        .map { article -> (article as Article).toUI() }
                    Resource.success(data)
                } catch (e: Exception) {
                    Resource.error(e, e.message)
                }
            }
        }
    }
}