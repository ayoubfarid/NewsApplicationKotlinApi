package com.farid.newsapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.farid.newsapplication.ui.base.NewsNavHost
import com.farid.newsapplication.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

/*
* This is the main activity of the app.
*   It is annotated with @AndroidEntryPoint to let Hilt know that it should inject Android classes like
*  FragmentActivity and Application.
*
 */
@AndroidEntryPoint
class NewsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // The onCreate() method is where you should initialize your activity.
        super.onCreate(savedInstanceState)
        setContent {
            // setContent() defines the activity's layout.
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    // NewsNavHost is a composable that hosts the navigation graph.
                    NewsNavHost()
                }
            }
        }
    }

}