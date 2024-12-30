package com.yarendemirkaya.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yarendemirkaya.home.ui.components.MovieItem


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.fetchMovies()
    }

    when {
        viewState.isLoading -> {
            // Show loading indicator
        }

        viewState.error != null -> {
            // Show error message
            Text(text = viewState.error!!)
        }

        viewState.movies.isNotEmpty() -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            ) {
                items(viewState.movies) { movie ->
                    MovieItem(
                        movie = movie,
                        onItemClick = {
                        }
                    )
                }
            }
        }
    }
}
