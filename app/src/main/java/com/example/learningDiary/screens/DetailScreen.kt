package com.example.learningDiary.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.learningDiary.uiComponents.SimpleAppBar
import com.example.learningDiary.uiComponents.MovieImages
import com.example.learningDiary.uiComponents.MovieRow
import com.example.learningDiary.viewModel.MoviesViewModel

@ExperimentalCoilApi
@Composable
fun DetailScreen(navController: NavController, moviesViewModel: MoviesViewModel, movieID: String?) {

    val movie = moviesViewModel.getMovieByID(movieID)
    val title = movie?.title ?: ""

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleAppBar(title = title) {
            navController.navigateUp()
        }
        if (movie != null) {
            MovieRow(movie = movie)
            MovieImages(images = movie.images)
        }
    }
}