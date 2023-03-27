package com.example.LearningDiary.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.LearningDiary.models.getMovieByID
import com.example.LearningDiary.uiComponents.SimpleAppBar
import com.example.LearningDiary.uiComponents.MovieImages
import com.example.learningDiary.MovieRow

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