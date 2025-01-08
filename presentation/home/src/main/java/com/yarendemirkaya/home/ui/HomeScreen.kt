package com.yarendemirkaya.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.yarendemirkaya.home.ui.components.Categories
import com.yarendemirkaya.home.ui.components.CustomSearchBar
import com.yarendemirkaya.home.ui.components.CustomTopAppBar
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HeaderSection(
                    viewState.categories.toList(),
                    onSearchQueryChange = { query ->
                            viewModel.onSearchTextChange(query)
                    },
                    onCategorySelected = { category ->
                        viewModel.filterMoviesByCategory(category)
                    },
                    onFilterClick = { filter ->
                        when (filter) {
                            "Fiyata Göre Artan" -> viewModel.generalFilter("price", true)
                            "Fiyata Göre Azalan" -> viewModel.generalFilter("price", false)
                            "Puana Göre Artan" -> viewModel.generalFilter("rating", true)
                            "Puana Göre Azalan" -> viewModel.generalFilter("rating", false)
                        }
                    })

                MovieGrid(
                    viewState.movies,
                    navController,
                    onCartClick = { movie ->
                        viewModel.insertMovie(movie)
                    }
                )
            }
        }
    }
}

@Composable
fun HeaderSection(
    categories: List<String>,
    onSearchQueryChange: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onFilterClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CustomTopAppBar()
        CustomSearchBar(onSearchQueryChange = onSearchQueryChange)
        Categories(categories, onCategorySelected = {
            onCategorySelected(it)
        }, onFilterClick = {
            onFilterClick(it)
        })
    }
}













