package com.farid.newsapplication.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.farid.newsapplication.R
import com.farid.newsapplication.data.database.entity.Article
import com.farid.newsapplication.ui.base.ShowError
import com.farid.newsapplication.ui.base.WebViewPage

@Composable
fun ArticleScreen(
    article: Article?,
) {
    val mContext = LocalContext.current

    Scaffold(

    ) {
        if (article?.url == null) {
            ShowError(text = stringResource(id = R.string.something_went_wrong))
        } else {
            WebViewPage(
                url = article.url,
                modifier = Modifier.padding(it)
            )
        }
    }
}