package com.farid.newsapplication.data.repository

import com.farid.newsapplication.common.Const
import com.farid.newsapplication.common.util.apiArticleListToArticleList
import com.farid.newsapplication.data.database.entity.Article
import com.farid.newsapplication.data.network.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val network: ApiInterface
) {

    suspend fun getNews(pageNumber: Int = Const.DEFAULT_PAGE_NUM): List<Article> {
        val articles = network.getNews(
            pageNum = pageNumber
        ).articles.apiArticleListToArticleList()
        return  articles

    }


    suspend fun searchNews(
        searchQuery: String,
        pageNumber: Int = Const.DEFAULT_PAGE_NUM
    ): Flow<List<Article>> =
        flow {
            emit(
                network.searchNews(searchQuery, pageNumber).articles.apiArticleListToArticleList()
            )
        }


}