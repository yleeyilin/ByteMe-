package com.example.byteme

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun MediaPickerRoot(){
    Column (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(16.dp)){
        Row(
            modifier = Modifier.padding(top = 40.dp)
        ) {
            Text(text = "ByteMe Influencer",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 95.dp)
            )
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, 
                contentDescription = "Top Bar",
                modifier = Modifier
                    .size(30.dp)
                    .padding(bottom = 5.dp))
            Surface(
                modifier = Modifier.size(50.dp)
            ) {}
            Icon(imageVector = Icons.Filled.Menu, 
                contentDescription = "Menu",
                modifier = Modifier.size(30.dp))
        }

        Row {
            Surface(
                modifier = Modifier.padding(start = 95.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.profilepic),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(16.dp)
                        .clip(CircleShape)
                )
            }
        }
        Row() {
            Text(
                text = "@byteme.influencer",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 105.dp)
            )
            Icon(imageVector = Icons.Filled.CheckCircleOutline,
                contentDescription = "Verified",
                tint = Color(0xff1ab9d1),
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(25.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(start = 40.dp, end = 10.dp)
            ) {
                Text(text = "1,283",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(text = "Following",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 3.dp))
            }
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 10.dp)
            )  {
                Text(text = "15.4M",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 12.dp))
                Text(text = "Followers",
                    fontSize = 17.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp))
            }
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 10.dp)
            )  {
                Text(text = "11.3B",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = "Likes",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(start = 6.dp),
                    color = Color.Gray)
            }
        }
        Row(
            modifier = Modifier.padding(top = 13.dp, start = 15.dp)
        ) {
            Card(onClick = { /*TODO*/ }, modifier = Modifier.size(width = 140.dp, height = 50.dp),
                colors = CardColors(contentColor = Color.White,
                    containerColor = Color.LightGray,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray)
            ) {
                Row (modifier = Modifier.padding(start = 23.dp, top = 13.dp)) {
                    Surface(
                        modifier = Modifier.size(5.dp),
                        color = Color.Transparent
                    ) {}
                    Text(text = "Edit profile", color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp)
                }
            }

            Surface(
                modifier = Modifier.size(10.dp)
            ) {}
            Card(onClick = { /*TODO*/ }, modifier = Modifier.size(width = 140.dp, height = 50.dp),
                colors = CardColors(contentColor = Color.White,
                    containerColor = Color.LightGray,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray)
            ) {
                Row (modifier = Modifier.padding(start = 15.dp, top = 13.dp)) {
                    Surface(
                        modifier = Modifier.size(5.dp),
                        color = Color.Transparent
                    ) {}
                    Text(text = "Share profile", color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp)
                }
            }
            Surface(
                modifier = Modifier.size(10.dp)
            ) {}
            Card(onClick = { /*TODO*/ }, modifier = Modifier.size(width = 50.dp, height = 50.dp),
                colors = CardColors(contentColor = Color.White,
                    containerColor = Color.LightGray,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray)
            ) {
                Row (modifier = Modifier.padding(start = 9.dp, top = 13.dp)) {
                    Surface(
                        modifier = Modifier.size(5.dp),
                        color = Color.Transparent
                    ) {}
                    Icon(imageVector = Icons.Filled.PersonAddAlt,
                        contentDescription = "Profile",
                        tint = Color.Black)
                }
            }
        }
        Surface(
            modifier = Modifier.size(20.dp)
        ) {}
        HorizontalDivider(
            color = Color.LightGray
        )
        Surface(
            modifier = Modifier.size(20.dp)
        ) {}
        Column {
            Surface(
                modifier = Modifier.padding(start = 140.dp, top = 10.dp, bottom = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.VideoLibrary,
                    contentDescription = "Video Image",
                    modifier = Modifier.size(100.dp),
                    tint = Color.LightGray
                )
            }
            Text(
                text = "Share a tiktok video",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 95.dp, bottom = 10.dp)
            )
            Surface(
                modifier = Modifier.padding(start = 125.dp, top = 10.dp)
            ) {
                PickVideo()
            }
        }
    }
}


@Composable
fun PickImage(){
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        result.value = it
    }

    Column {
        Button(onClick = {
            launcher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "Select Image")
        }

        result.value?.let { image ->
            //Use Coil to display the selected image
            val painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = image)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp, 150.dp)
                    .padding(16.dp)
            )
        }
    }
}



@Composable
fun PickMultipleImage(){
    val result = remember { mutableStateOf<List<Uri?>?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) {
        result.value = it
    }

    Column {
        Button(onClick = {
            launcher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "Select Multiple Image")
        }

        result.value?.let { images ->
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
                items(images){
                    //Use Coil to display the selected image
                    val painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(data = it)
                            .build()
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.size(150.dp, 150.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PickVideo(){
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        result.value = it
    }

    Column {
        Card(onClick = {
            launcher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly)
            )
        }, modifier = Modifier.size(width = 130.dp, height = 50.dp).alpha(0.8F),
            colors = CardColors(contentColor = Color.White,
                containerColor = Color(0xfff10e2a),
                disabledContentColor = Color.White,
                disabledContainerColor = Color(0xfff10e2a))
        ) {
            Row (modifier = Modifier.padding(start = 22.dp, top = 13.dp)) {
                Icon(imageVector = Icons.Filled.Upload, contentDescription = "Post")
                Surface(
                    modifier = Modifier.size(5.dp),
                    color = Color.Transparent
                ) {}
                Text(text = "Upload")
            }
        }

        result.value?.let { image ->
            Text(text = "Video Path: "+image.path.toString())
        }
    }
}

@Composable
fun PickDocument(){
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
        result.value = it
    }

    Column {
        Button(onClick = {
            launcher.launch(arrayOf("application/pdf"))
        }) {
            Text(text = "Select Document")
        }
        result.value?.let { image ->
            Text(text = "Document Path: "+image.path.toString())
        }
    }
}