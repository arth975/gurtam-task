package com.gurtam.task.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gurtam.task.domain.models.NewsSource
import com.gurtam.task.domain.usecases.GetAllNewsSourcesUseCase
import com.gurtam.task.domain.models.ResultOf
import com.gurtam.task.models.NewsSourceUI
import com.gurtam.task.models.mappers.toUI
import com.gurtam.task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourcesViewModel @Inject constructor(
    private val getAllNewsSourcesUseCase: GetAllNewsSourcesUseCase
) : ViewModel() {

    private val mNewsSourceLiveData = MutableLiveData<Resource<List<NewsSourceUI>>>()
    val newsSourceLiveData: LiveData<Resource<List<NewsSourceUI>>> = mNewsSourceLiveData

    fun fetchNewsSources() {
        viewModelScope.launch {
            getAllNewsSourcesUseCase().collectLatest { result ->
                mNewsSourceLiveData.value = when (result) {
                    is ResultOf.Success<List<NewsSource>> -> {
                        val data = result.data.map { it.toUI() }
                        Resource.success(data)
                    }

                    is ResultOf.Error<*> -> Resource.error(result.e, result.message)
                }
            }
        }
    }
}