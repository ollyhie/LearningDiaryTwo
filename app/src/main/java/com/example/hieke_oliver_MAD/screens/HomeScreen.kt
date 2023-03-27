package com.example.hieke_oliver_MAD.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.hieke_oliver_MAD.Navigation
import com.example.hieke_oliver_MAD.models.getMovies
import com.example.hieke_oliver_MAD.uiComponents.MovieList
import com.example.hieke_oliver_MAD.uiComponents.TopAppBar

@ExperimentalCoilApi
@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar {
            navController.navigate(route = Navigation.FavouriteScreen.route)
        }
        MovieList(navController = navController, movies = getMovies())
    }
}