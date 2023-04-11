package com.example.learningDiary.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.learningDiary.uiComponents.SimpleAppBar
import com.example.learningDiary.uiComponents.MovieList
import com.example.learningDiary.viewModel.MoviesViewModel

@ExperimentalCoilApi
@Composable
fun FavouritesScreen(navController: NavController, moviesViewModel: MoviesViewModel) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleAppBar(title = "Favourites") {
            navController.navigateUp()
        }
            MovieList(
                navController = navController,
                moviesViewModel = moviesViewModel,
                movies = moviesViewModel.favouredMovies
            )
    }
}

