package com.example.byteme

import android.media.ThumbnailUtils
import android.os.Build
import android.os.CancellationSignal
import android.util.Size
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Blender
import androidx.compose.material.icons.filled.BorderOuter
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Drafts
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.HdrPlus
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun PostingScreen(packageName: String) {
    Column {
        TopBar(packageName = packageName)
        Surface(
            modifier = Modifier.size(20.dp),
            color = Color.Transparent
        ) {}
        StaticToggleSet()
    }
    //TODO: Combine top bar + toggle set + buttons
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TopBar(packageName: String) {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Row {
            Icon(imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .padding(15.dp)
                    .size(30.dp))
            Text(
                text = "Post",
                modifier = Modifier.padding(start = 123.dp, end = 80.dp, top = 17.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 200.dp)
                .background(color = Color.Transparent)
                .fillMaxWidth()
        ) {
            Text(
                text = "Describe your video",
                modifier = Modifier.padding(top = 17.dp, start = 15.dp),
                color = Color.LightGray,
                fontWeight = FontWeight.SemiBold
            )
            Surface(
                modifier = Modifier.size(40.dp),
                color = Color.Transparent
            ) {}
//            val videoResourceId = R.raw.original
//            val filePath = "android.resource://$packageName/$videoResourceId"
//            val bitmap = ThumbnailUtils.createVideoThumbnail(
//                File(filePath),
//                Size(128, 128),
//                CancellationSignal()
//            )
//            Image(
//                bitmap = bitmap.asImageBitmap(),
//                contentDescription = "Original Video Thumbnail",
//                modifier = Modifier
//                    .size(210.dp) // Set the size of the thumbnail
//                    .align(Alignment.Top) // Align the thumbnail at the top of its container
//            )
            Image(
                imageVector = Icons.Default.Photo,
//                imageVector = ImageVector.vectorResource(id = R.raw.original),
                contentDescription = "Placeholder for video thumbnail",
                modifier = Modifier
                    .size(210.dp)
                    .align(Alignment.Top).alpha(0.3F)
            )
        }
        Row() {
            Surface(
                modifier = Modifier.size(20.dp),
                color = Color.Transparent
            ) {}
            Text(text = "# Hashtags", fontWeight = FontWeight.Medium, fontSize = 12.sp)
            Surface(
                modifier = Modifier.size(20.dp),
                color = Color.Transparent
            ) {}
            Text(text = "@ mention", fontWeight = FontWeight.Medium, fontSize = 12.sp)
            Surface(
                modifier = Modifier.size(20.dp),
                color = Color.Transparent
            ) {}
            Image(imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play button", modifier = Modifier
                    .size(20.dp)
                    .padding(top = 3.dp))
            Text(text = "Videos", fontWeight = FontWeight.Medium, fontSize = 12.sp)
        }
    }
}

//fun getThumbSize(path: String?): Size {
//    val dataRetriever = MediaMetadataRetriever()
//    dataRetriever.setDataSource(path)
//    val width =
//        dataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
//    val height =
//        dataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
//    return Size(width!!.toInt(), height!!.toInt())
//}

@Composable
fun StaticToggleSet() {
    var checkedTag by remember { mutableStateOf(false) }
    var checkedDuet by remember { mutableStateOf(true) }
    var checkedStitch by remember { mutableStateOf(false) }
    var checkedAdBlend by remember { mutableStateOf(false) }
    //TODO: Make toggle set
    Column {
        Row() {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Toggle 1 left",
                modifier = Modifier
                    .padding(end = 10.dp, start = 20.dp, top = 10.dp)
                    .size(30.dp))
            Text(text = "Tag People", modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 13.dp),
                fontSize = 15.sp)
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Toggle 1 right",
                modifier = Modifier
                    .padding(end = 10.dp, start = 200.dp, top = 10.dp)
                    .size(30.dp))
        }
        Surface(
            modifier = Modifier.size(10.dp),
            color = Color.Transparent
        ) {}
        Row() {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "Toggle 2 left",
                modifier = Modifier
                    .padding(end = 10.dp, start = 20.dp, top = 10.dp)
                    .size(30.dp))
            Text(text = "Who can watch this video", modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 13.dp),
                fontSize = 15.sp)
            Text(text = "Everyone", modifier = Modifier.padding(end = 10.dp, start = 25.dp, top = 13.dp),
                fontSize = 15.sp, color = Color.LightGray, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic
            )
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Toggle 2 right",
                modifier = Modifier
                    .padding(end = 10.dp, top = 10.dp)
                    .size(30.dp))
        }
        Surface(
            modifier = Modifier.size(10.dp),
            color = Color.Transparent
        ) {}
        Row() {
            Icon(imageVector = Icons.Filled.Comment, contentDescription = "Toggle 1 left",
                modifier = Modifier
                    .padding(end = 10.dp, start = 20.dp, top = 10.dp)
                    .size(30.dp))
            Text(text = "Tag People", modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 13.dp),
                fontSize = 15.sp)
            Switch(checked = checkedTag, onCheckedChange = { checkedTag = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xffadebca),
                    checkedTrackColor = Color(0xff47cd84),
                    uncheckedThumbColor = Color(0xffd4d4d4),
                    uncheckedTrackColor = Color(0xff5e5e5e),
                ),
                modifier = Modifier
                    .padding(end = 20.dp, start = 170.dp))
        }
        Surface(
            modifier = Modifier.size(10.dp),
            color = Color.Transparent
        ) {}
        Row() {
            Icon(imageVector = Icons.Filled.PeopleAlt, contentDescription = "Toggle 2 left",
                modifier = Modifier
                    .padding(end = 10.dp, start = 20.dp, top = 10.dp)
                    .size(30.dp))
            Text(text = "Allow Duet", modifier = Modifier.padding(end = 10.dp, start = 13.dp, top = 13.dp),
                fontSize = 15.sp)
            Switch(checked = checkedDuet, onCheckedChange = { checkedDuet = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xffadebca),
                    checkedTrackColor = Color(0xff47cd84),
                    uncheckedThumbColor = Color(0xffd4d4d4),
                    uncheckedTrackColor = Color(0xff5e5e5e),
                ),
                modifier = Modifier
                    .padding(end = 20.dp, start = 170.dp))
        }
        Surface(
            modifier = Modifier.size(10.dp),
            color = Color.Transparent
        ) {}
        Row() {
            Icon(imageVector = Icons.Filled.BorderOuter, contentDescription = "Toggle 3 left",
                modifier = Modifier
                    .padding(end = 10.dp, start = 20.dp, top = 10.dp)
                    .size(30.dp))
            Text(text = "Allow Stitch", modifier = Modifier.padding(end = 10.dp, start = 13.dp, top = 13.dp),
                fontSize = 15.sp)
            Switch(checked = checkedStitch, onCheckedChange = { checkedStitch = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xffadebca),
                    checkedTrackColor = Color(0xff47cd84),
                    uncheckedThumbColor = Color(0xffd4d4d4),
                    uncheckedTrackColor = Color(0xff5e5e5e),
                ),
                modifier = Modifier
                    .padding(end = 20.dp, start = 161.dp))
        }
        Surface(
            modifier = Modifier.size(10.dp),
            color = Color.Transparent
        ) {}
        Row() {
            Icon(imageVector = Icons.Filled.Blender, contentDescription = "Toggle 4 left",
                modifier = Modifier
                    .padding(end = 10.dp, start = 20.dp, top = 10.dp)
                    .size(30.dp))
            Text(text = "Allow AdBlend", modifier = Modifier.padding(end = 10.dp, start = 13.dp, top = 13.dp),
                fontSize = 15.sp)
            Switch(checked = checkedAdBlend, onCheckedChange = { checkedAdBlend = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xffadebca),
                    checkedTrackColor = Color(0xff47cd84),
                    uncheckedThumbColor = Color(0xffd4d4d4),
                    uncheckedTrackColor = Color(0xff5e5e5e),
                ),
                modifier = Modifier
                    .padding(end = 20.dp, start = 143.dp))
        }
        Surface(
            modifier = Modifier.size(35.dp),
            color = Color.Transparent
        ) {}
        Column {
            Text(text = "Automatically share to:", fontSize = 12.sp,
                modifier = Modifier.padding(start = 24.dp),
                fontStyle = FontStyle.Italic,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
            Row {
                Surface(
                    modifier = Modifier.size(25.dp),
                    color = Color.Transparent
                ) {}
                Icon(imageVector = Icons.Filled.PhotoCamera, contentDescription = "Instagram",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 5.dp),
                    tint = Color.LightGray)
                Surface(
                    modifier = Modifier.size(20.dp),
                    color = Color.Transparent
                ) {}
                Icon(imageVector = Icons.Default.HdrPlus, contentDescription = "Add smth",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 5.dp),
                    tint = Color.LightGray)
                Surface(
                    modifier = Modifier.size(20.dp),
                    color = Color.Transparent
                ) {}
                Icon(imageVector = Icons.Filled.Facebook, contentDescription = "Facebook",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 5.dp),
                    tint = Color.LightGray)
            }
        }
        Surface(
            modifier = Modifier.size(5.dp),
            color = Color.Transparent
        ) {}
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 27.dp, top = 30.dp)) {
            Card(onClick = { /*TODO*/ }, modifier = Modifier.size(width = 170.dp, height = 60.dp)) {
                Row(modifier = Modifier.padding(start = 47.dp, top = 20.dp)) {
                    Icon(imageVector = Icons.Filled.Drafts, contentDescription = "Draft")
                    Surface(
                        modifier = Modifier.size(5.dp),
                        color = Color.Transparent
                    ) {}
                    Text(text = "Drafts")
                }
            }
            Surface(
                modifier = Modifier.size(20.dp),
                color = Color.Transparent
            ) {}
            Card(onClick = { /*TODO*/ }, modifier = Modifier.size(width = 170.dp, height = 60.dp),
                colors = CardColors(contentColor = Color.White,
                    containerColor = Color(0xfff10e2a),
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color(0xfff10e2a))) {
                Row (modifier = Modifier.padding(start = 50.dp, top = 18.dp)){
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
