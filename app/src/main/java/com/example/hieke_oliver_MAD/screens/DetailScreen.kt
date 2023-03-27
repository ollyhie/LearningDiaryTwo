package com.example.hieke_oliver_MAD.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.hieke_oliver_MAD.models.getMovieByID
import com.example.hieke_oliver_MAD.uiComponents.MovieRow
import com.example.hieke_oliver_MAD.uiComponents.SimpleAppBar
import com.example.hieke_oliver_MAD.uiComponents.MovieImages

@ExperimentalCoilApi
@Composable
fun DetailScreen(navController: NavController, movieID: String?) {

    val movie = getMovieByID(movieID)
    val title = movie?.title ?: ""

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleAppBar(title = title) {
            navController.navigateUp()
        }
        if (movie != null) {
            MovieRow(movie = movie, openDetailScreen = {})
            MovieImages(images = movie.images)
        }
    }
}