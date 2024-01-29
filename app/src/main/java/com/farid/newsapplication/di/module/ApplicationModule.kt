package com.farid.newsapplication.di.module

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.work.WorkManager
import com.farid.newsapplication.common.Const
import com.farid.newsapplication.common.dispatcher.DefaultDispatcherProvider
import com.farid.newsapplication.common.dispatcher.DispatcherProvider
import com.farid.newsapplication.common.networkhelper.NetworkHelper
import com.farid.newsapplication.common.networkhelper.NetworkHelperImpl
import com.farid.newsapplication.data.database.entity.Article
import com.farid.newsapplication.data.network.ApiInterface
import com.farid.newsapplication.data.network.ApiKeyInterceptor
import com.farid.newsapplication.di.ApiKey
import com.farid.newsapplication.di.BaseUrl
import com.farid.newsapplication.ui.paging.NewsPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {



    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @ApiKey
    @Provides
    fun provideApiKey(): String = Const.API_KEY

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = Const.BASE_URL



    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonFactory: GsonConverterFactory,
        apiKeyInterceptor: ApiKeyInterceptor
    ): ApiInterface {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

        return Retrofit
            .Builder()
            .client(client) //adding client to intercept all responses
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .build()
            .create(ApiInterface::class.java)
    }



    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun providePager(
        newsPagingSource: NewsPagingSource
    ): Pager<Int, Article> {
        return Pager(
            config = PagingConfig(
                Const.DEFAULT_QUERY_PAGE_SIZE
            )
        ) {
            newsPagingSource
        }
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ): NetworkHelper {
        return NetworkHelperImpl(context)
    }


    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context
    ): WorkManager {
        return WorkManager.getInstance(context)
    }

}