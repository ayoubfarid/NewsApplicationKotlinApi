package com.farid.newsapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.farid.newsapplication.common.Const
import com.farid.newsapplication.data.database.entity.Article
import com.farid.newsapplication.ui.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val pager: Pager<Int, Article>,
) : ViewModel() {

    private val _newsItem = MutableStateFlow<UIState<List<Article>>>(UIState.Empty)
    val newsItem: StateFlow<UIState<List<Article>>> = _newsItem

    private val _newsItemPaging = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val newsItemPaging: StateFlow<PagingData<Article>> = _newsItemPaging

    init {
        fetchNews()
    }
    fun fetchNews() {
            fetchNewsWithoutFilter()
    }

    private fun fetchNewsWithoutFilter() {

        viewModelScope.launch {
            pager.flow.cachedIn(viewModelScope)
                .map {
                    it.filter { article ->
                        article.title?.isNotEmpty() == true &&
                                article.urlToImage?.isNotEmpty() == true
                    }
                }
                .collect {
                    _newsItemPaging.value = it
                }
        }
    }








}