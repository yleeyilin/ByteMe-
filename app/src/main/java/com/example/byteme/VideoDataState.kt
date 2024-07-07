package com.example.byteme

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class VideoDataState() {
    var VideoURI by mutableStateOf("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        private set

    var adBlend by mutableStateOf(false)
        private set

    fun onVideoClick(image: Uri) {
        VideoURI = image.toString()
    }

    fun onAdBlendClick() {
        adBlend = !adBlend
    }
}