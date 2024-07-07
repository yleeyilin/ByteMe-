package com.example.byteme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Iso
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.StickyNote2
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun BufferingScreen(onFinished: () -> Unit) {
    val tiktokAnimationComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.tiktok
        )
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
    }
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Black)
    ) {
        Surface(
            modifier = Modifier
                .padding(top = 450.dp, start = 170.dp),
            color = Color.Transparent
        ) {
            LottieAnimation(
                composition = tiktokAnimationComposition,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(60.dp))
        }
        Surface(
            modifier = Modifier.size(324.dp),
            color = Color.Transparent
        ) {}
        Row() {
            Surface(
                modifier = Modifier.size(20.dp),
                color = Color.Transparent
            ) {}
            Column() {
                Icon(
                    imageVector = Icons.Filled.LibraryMusic,
                    contentDescription = "Sound",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Text(text = "Sound",
                    fontSize = 12.sp,
                    color = Color.White)
            }
            Surface(
                modifier = Modifier.size(26.dp),
                color = Color.Transparent
            ) {}
            Column {
                Icon(
                    imageVector = Icons.Default.Iso,
                    contentDescription = "Effects",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 2.dp, bottom = 2.dp)
                )
                Text(text = "Effects",
                    fontSize = 12.sp,
                    color = Color.White)
            }
            Surface(
                modifier = Modifier.size(24.dp),
                color = Color.Transparent
            ) {}
            Column {
                Icon(
                    imageVector = Icons.Filled.TextFields,
                    contentDescription = "Text",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(bottom = 2.5.dp)
                )
                Text(text = "Text",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp),
                    color = Color.White)
            }
            Surface(
                modifier = Modifier.size(30.dp),
                color = Color.Transparent
            ) {}
            Column {
                Icon(
                    imageVector = Icons.Filled.StickyNote2,
                    contentDescription = "Sticker",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 2.dp),
                    tint = Color.White
                )
                Text(text = "Sticker",
                    fontSize = 12.sp,
                    color = Color.White)
            }
            Surface(
                modifier = Modifier.size(30.dp),
                color = Color.Transparent
            ) {}
            Card(onClick = { /* do smth */ }, modifier = Modifier.size(width = 100.dp, height = 56.dp),
                colors = CardColors(contentColor = Color.White,
                    containerColor = Color(0xfff10e2a),
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color(0xfff10e2a))
            ) {
                Row (modifier = Modifier.padding(start = 17.dp, top = 15.dp)) {
                    Icon(imageVector = Icons.Filled.Upload, contentDescription = "Post")
                    Surface(
                        modifier = Modifier.size(5.dp),
                        color = Color.Transparent
                    ) {}
                    Text(text = "Post")
                }
            }
            LaunchedEffect(Unit) {
                delay(2000) // Delay for 2 seconds
                onFinished()
            }
        }
    }
}