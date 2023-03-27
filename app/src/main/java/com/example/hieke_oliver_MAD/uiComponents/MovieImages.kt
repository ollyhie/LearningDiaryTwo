package com.example.hieke_oliver_MAD.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun MovieImages(images: List<String>) {

    Text(
        text = "Movie Images",
        textAlign = TextAlign.Center,
        fontSize = MaterialTheme.typography.h4.fontSize,
        modifier = Modifier.fillMaxWidth()
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(images) { image ->
            Card(
                modifier = Modifier
                    .width(500.dp)
                    .height(400.dp)
            ) {
                val painter = rememberImagePainter(
                    data = image,
                    builder = {
                        error(R.drawable.avatar2)
                        crossfade(500)
                    }
                )

                val state = painter.state
                if (state is ImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }

                Image(
                    painter = painter,
                    contentDescription = "Image ${images.indexOf(image)}",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}