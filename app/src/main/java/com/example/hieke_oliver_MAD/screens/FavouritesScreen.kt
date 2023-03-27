package com.example.LearningDiary.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.LearningDiary.models.getFavouredMovies

import com.example.LearningDiary.uiComponents.SimpleAppBar
import com.example.learningDiary.MovieList

@ExperimentalCoilApi
@Composable
fun FavouritesScreen(navController: NavController) {

    val movies = getFavouredMovies()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleAppBar(title = "Favourites") {
            navController.navigateUp()
        }
        if (movies != null) {
            MovieList(navController = navController, movies = movies)
        }
    }
}

