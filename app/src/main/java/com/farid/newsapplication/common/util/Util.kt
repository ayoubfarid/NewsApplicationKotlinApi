package com.farid.newsapplication.common.util

import com.farid.newsapplication.data.database.entity.Article

fun List<Article>.filterArticles(): List<Article> {
    return this.filterNot { article ->
        article.title.isNullOrEmpty() || article.description.isNullOrEmpty() || article.urlToImage.isNullOrEmpty()
    }
}