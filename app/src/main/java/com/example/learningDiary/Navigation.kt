package com.example.learningDiary

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MyNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "homeScreen"
    ) {
        composable(
            "homeScreen") {HomeScreen(navController = navController)}
        composable(
            "detailScreen/{movieID}",
            arguments = listOf(navArgument("movieID") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            DetailScreen(
                navController = navController,
                movieID = navBackStackEntry.arguments?.getString("movieID"))
        }
    }
}