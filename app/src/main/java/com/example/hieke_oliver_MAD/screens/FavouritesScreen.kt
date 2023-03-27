package com.example.hieke_oliver_MAD.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.hieke_oliver_MAD.models.getFavouredMovies
import com.example.hieke_oliver_MAD.uiComponents.MovieList
import com.example.hieke_oliver_MAD.uiComponents.SimpleAppBar

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

