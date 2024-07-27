package com.example.byteme

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

@SuppressLint("MutableCollectionMutableState")
class FirebaseFetcher() {
    private var VideoURI by mutableStateOf(mutableListOf<String>())
        private set

    private var storageRef = FirebaseStorage.getInstance().reference

    fun downloadAllImages() {
        val uris = mutableListOf<String>()
        storageRef.listAll()
            .addOnSuccessListener { listResult ->
                val itemRefs = listResult.items
                for (itemRef in itemRefs) {
                    itemRef.downloadUrl.addOnSuccessListener { uri ->
                        uris.add(uri.toString())
                    }
                }
            }
        VideoURI = uris
    }
}