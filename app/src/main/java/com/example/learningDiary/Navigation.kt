package com.example.learningDiary

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.learningDiary.screens.AddMovieScreen
import com.example.learningDiary.screens.DetailScreen
import com.example.learningDiary.screens.FavouritesScreen
import com.example.learningDiary.screens.HomeScreen
import com.example.learningDiary.viewModel.MoviesViewModel

const val ARG_DETAIL_MOV_ID = "movieID"

sealed class Navigation(val route: String) {

    object HomeScreen : Navigation(route = "homeScreen")
    object FavouriteScreen : Navigation(route = "favouriteScreen")
    object AddMovieScreen : Navigation(route = "addMovieScreen")
    object DetailScreen : Navigation(route = "detailScreen/{$ARG_DETAIL_MOV_ID}") {
        fun setID(movieID: String): String {
            return this.route.replace(oldValue = "{$ARG_DETAIL_MOV_ID}",
                newValue = movieID)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun NavigationController(moviesViewModel: MoviesViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.HomeScreen.route
    ) {
        composable(
            route = Navigation.HomeScreen.route
        ) { HomeScreen(
            navController = navController,
            moviesViewModel = moviesViewModel
        ) }
        composable(
            Navigation.DetailScreen.route,
            arguments = listOf(navArgument(ARG_DETAIL_MOV_ID) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            DetailScreen(
                navController = navController,
                moviesViewModel = moviesViewModel,
                movieID = navBackStackEntry.arguments?.getString(ARG_DETAIL_MOV_ID)
            )
        }
        composable(
            Navigation.FavouriteScreen.route
        ) { FavouritesScreen(
            navController = navController,
            moviesViewModel = moviesViewModel
        ) }
        composable(
            Navigation.AddMovieScreen.route
        ) { AddMovieScreen(
            navController = navController,
            moviesViewModel = moviesViewModel
        ) }
    }
}