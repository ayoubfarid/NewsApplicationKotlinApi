package com.farid.newsapplication.ui.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
//
import com.farid.newsapplication.R

//


sealed class Route(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int,
    val routeWithoutArgs: String = route
) {
    object TopNews :
        Route("topNews/{country}/{language}/{source}", R.string.news, R.drawable.ic_news, "topNews")

    object SearchNews : Route("searchNews", R.string.search, R.drawable.ic_search)
    object NewsArticle :
        Route("newsArticle/{article}", R.string.news, R.drawable.ic_news, "newsArticle")
}

val bottomBarScreens = listOf(
    Route.TopNews,
    Route.SearchNews
)

