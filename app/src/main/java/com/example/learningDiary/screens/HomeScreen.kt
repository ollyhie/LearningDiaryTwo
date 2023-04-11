package com.example.learningDiary.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.learningDiary.Navigation
import com.example.learningDiary.models.getMovies
import com.example.learningDiary.uiComponents.TopAppBar
import com.example.learningDiary.uiComponents.MovieList
import com.example.learningDiary.viewModel.MoviesViewModel
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun HomeScreen(navController: NavController, moviesViewModel: MoviesViewModel) {

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
            movies = moviesViewModel.movies
        )
    }
}