package com.example.learningDiary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.learningDiary.models.getMovieByID

@ExperimentalCoilApi
@Composable
fun DetailScreen(navController: NavController, movieID: String?) {

    val movie = getMovieByID(movieID)

    if (movie != null) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DetailScreenAppBar(title = movie.title) {
                navController.navigateUp()
            }
            MovieRow(movie = movie, openDetailScreen = {})
            MovieImages(images = movie.images)
        }
    }
}

@Composable
fun DetailScreenAppBar(title: String, goBack: () -> Unit) {

    TopAppBar(
        backgroundColor = Color(0xff006d65),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                goBack()
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                )
            }
        }
    )
}

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