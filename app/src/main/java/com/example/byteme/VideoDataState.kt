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
        adBlend = !adBlend // Toggle the adBlend state

        // Update VideoURI based on the new state of adBlend
        VideoURI = if (adBlend) {
            FileMap.hshMap[VideoURI] ?: VideoURI // Get the blended video URI if adBlend is true
        } else {
            FileMap.revHshMap[VideoURI] ?: VideoURI // Get the original video URI if adBlend is false
        }
    }
}