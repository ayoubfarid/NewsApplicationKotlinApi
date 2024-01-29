package com.farid.newsapplication.data.network

import com.farid.newsapplication.data.model.News
import com.farid.newsapplication.common.Const
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = Const.DEFAULT_COUNTRY,
        @Query("page") pageNum: Int = Const.DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = Const.DEFAULT_QUERY_PAGE_SIZE,
    ): News



    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNum: Int = Const.DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = Const.DEFAULT_QUERY_PAGE_SIZE,
    ): News

}