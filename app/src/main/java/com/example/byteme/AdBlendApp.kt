package com.example.byteme

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AdBlendApp(
    navController: NavHostController = rememberNavController()
) {
    val videoDataState = VideoDataState()

    NavHost(
        navController = navController,
        startDestination = "Video-picker",
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable("Video-picker") {
            MediaPickerRoot(
                onVideoPicked = {
                    navController.navigate("Posting-preview")
                },
                videoDataState
            )
        }
        composable("Posting-preview") {
            PostingScreen(
                onAdBlendSelect = {
                    videoDataState.onAdBlendClick()
                    navController.navigate("Buffering")
                },
                onBackNavigation = {
                    navController.navigate("Video-picker")
                },
                uri = Uri.parse(videoDataState.VideoURI),
                adBlend = videoDataState.adBlend,
            )
        }
        composable("Gen-video") {
            VideoScreen(
                videoURI = videoDataState.VideoURI,
                onPostClick = {
                    navController.navigate("Posting-preview")
                })
        }
        composable("Buffering") {
            BufferingScreen(
                onFinished = {
                    navController.navigate("Gen-video")
                }
            )
        }
    }
}
