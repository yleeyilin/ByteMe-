package com.example.byteme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Iso
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MusicVideo
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.StickyNote2
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

/**
 * Composable function that displays an ExoPlayer to play a video using Jetpack Compose.
 *
 * @OptIn annotation to UnstableApi is used to indicate that the API is still experimental and may
 * undergo changes in the future.
 *
 * @see EXAMPLE_VIDEO_URI Replace with the actual URI of the video to be played.
 */
@Composable
fun VideoScreen() {
    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    // Create a MediaSource
    val mediaSource = remember("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4") {
        MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
    }

    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    // Manage lifecycle events
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Use AndroidView to embed an Android View (PlayerView) into Compose
    Column {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(820.dp) // Set your desired height
        )
        Surface(
            modifier = Modifier.size(13.dp),
            color = Color.Transparent
        ) {}
        Row() {
            Surface(
                modifier = Modifier.size(20.dp),
                color = Color.Transparent
            ) {}
            Column {
                Icon(
                    imageVector = Icons.Filled.LibraryMusic,
                    contentDescription = "Sound",
                    modifier = Modifier.size(40.dp)
                )
                Text(text = "Sound",
                    fontSize = 12.sp)
            }
            Surface(
                modifier = Modifier.size(26.dp),
                color = Color.Transparent
            ) {}
            Column {
                Icon(
                    imageVector = Icons.Default.Iso,
                    contentDescription = "Effects",
                    modifier = Modifier.size(40.dp).padding(start = 2.dp, bottom = 2.dp)
                )
                Text(text = "Effects",
                    fontSize = 12.sp)
            }
            Surface(
                modifier = Modifier.size(24.dp),
                color = Color.Transparent
            ) {}
            Column {
                Icon(
                    imageVector = Icons.Filled.TextFields,
                    contentDescription = "Text",
                    modifier = Modifier.size(40.dp).padding(bottom = 2.5.dp)
                )
                Text(text = "Text",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp))
            }
            Surface(
                modifier = Modifier.size(30.dp),
                color = Color.Transparent
            ) {}
            Column {
                Icon(
                    imageVector = Icons.Filled.StickyNote2,
                    contentDescription = "Sticker",
                    modifier = Modifier.size(40.dp).padding(start = 2.dp)
                )
                Text(text = "Sticker",
                    fontSize = 12.sp)
            }
            Surface(
                modifier = Modifier.size(30.dp),
                color = Color.Transparent
            ) {}
            Card(onClick = { /*TODO*/ }, modifier = Modifier.size(width = 100.dp, height = 56.dp),
                colors = CardColors(contentColor = Color.White,
                    containerColor = Color(0xfff10e2a),
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color(0xfff10e2a))
            ) {
                Row (modifier = Modifier.padding(start = 17.dp, top = 18.dp)) {
                    Icon(imageVector = Icons.Filled.Upload, contentDescription = "Post")
                    Surface(
                        modifier = Modifier.size(5.dp),
                        color = Color.Transparent
                    ) {}
                    Text(text = "Post")
                }
            }
        }
    }
}