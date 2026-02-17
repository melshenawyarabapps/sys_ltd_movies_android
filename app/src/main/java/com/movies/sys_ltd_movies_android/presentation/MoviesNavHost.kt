package com.movies.sys_ltd_movies_android.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movies.sys_ltd_movies_android.presentation.main.MainScreen
import com.movies.sys_ltd_movies_android.presentation.trailer.TrailerScreen

@Composable
fun MoviesNavHost(
    navController: NavHostController,
    onShowMoviesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier
    ) {
        composable(Screen.Main.route) {
            MainScreen(onShowMoviesClick = onShowMoviesClick)
        }
        composable(
            route = Screen.Trailer.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            TrailerScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
