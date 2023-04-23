package com.example.learningDiary.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.learningDiary.Navigation
import com.example.learningDiary.uiComponents.TopAppBar
import com.example.learningDiary.uiComponents.MovieList
import com.example.learningDiary.viewModel.MoviesViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalCoilApi
@Composable
fun HomeScreen(navController: NavController, moviesViewModel: MoviesViewModel) {

    println("------------------------------------")
    println("THESE ARE THE MOVIES:")
    moviesViewModel.movies.value.forEach {movie ->
        println("Movie: " + movie.title)
    }
    println(moviesViewModel.movies.toString())
    println("------------------------------------")
    println()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            onOpenFavouritesClicked = { navController.navigate(
                route = Navigation.FavouriteScreen.route
            )},
            onAddMovieClicked = { navController.navigate(
                route = Navigation.AddMovieScreen.route
            )}
        )
        MovieList(
            navController = navController,
            moviesViewModel = moviesViewModel,
            movies = moviesViewModel.movies.collectAsState()
        )
    }
}