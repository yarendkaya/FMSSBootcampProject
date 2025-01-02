package com.yarendemirkaya.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.yarendemirkaya.home.ui.components.MovieGrid


@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.fetchMovies()
    }

    when {
        viewState.isLoading -> {

        }
        viewState.error != null -> {

            Text(text = viewState.error!!)
        }
        viewState.movies.isNotEmpty() -> {
            MovieGrid(viewState.movies, viewState.categories.toList(), navController)
        }
    }
}









