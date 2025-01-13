package com.yarendemirkaya.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieModel

@Composable
fun MovieGrid(
    movies: List<MovieModel>,
    onMovieClick: (MovieModel) -> Unit,
    onCartClick: (InsertMovieModel) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onMovieClick = onMovieClick,
                onCartClick = {
                    val insertMovieModel = InsertMovieModel(
                        name = movie.name,
                        image = movie.image,
                        price = movie.price,
                        category = movie.category,
                        rating = movie.rating,
                        year = movie.year,
                        director = movie.director,
                        description = movie.description,
                        orderAmount = 1,
                    )
                    onCartClick(insertMovieModel)
                }
            )
        }
    }
}

