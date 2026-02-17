package com.movies.sys_ltd_movies_android.presentation

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Trailer : Screen("trailer/{movieId}") {
        fun createRoute(movieId: Int) = "trailer/$movieId"
    }
}
