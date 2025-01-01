package com.yarendemirkaya.home.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.yarendemirkaya.domain.model.MovieModel

@Composable
fun MovieGrid(
    movies: List<MovieModel>,
    categories: List<String>,
    navController: NavController,
    onSearchQueryChange: (String) -> Unit = {},
    onCartClick: (MovieModel) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(bottom = 88.dp), // Bottom bar yüksekliği kadar padding
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {

        item(span = { GridItemSpan(2) }) {
            HeaderSection(categories, onSearchQueryChange)
        }

        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onItemClick = {
                    val movieJson = Uri.encode(Gson().toJson(movie))
                    navController.navigate("detail/$movieJson")
                },
                onCartClick = { onCartClick(movie) }
            )
        }
    }
}

@Composable
fun HeaderSection(
    categories: List<String>,
    onSearchQueryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTopAppBar()
        CustomSearchBar(onSearchQueryChange = onSearchQueryChange)
        Categories(categories)
    }
}