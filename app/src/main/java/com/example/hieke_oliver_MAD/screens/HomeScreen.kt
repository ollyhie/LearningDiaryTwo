package com.example.LearningDiary.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.LearningDiary.Navigation
import com.example.LearningDiary.models.getMovies
import com.example.LearningDiary.uiComponents.TopAppBar
import com.example.learningDiary.MovieList

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