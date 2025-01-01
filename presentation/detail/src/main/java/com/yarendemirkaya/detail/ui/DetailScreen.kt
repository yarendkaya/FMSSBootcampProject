package com.yarendemirkaya.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yarendemirkaya.domain.model.MovieModel

@Composable
fun DetailScreen(movie: MovieModel) {

    DetailItem(movie = movie)
}

@Composable
fun DetailItem(movie: MovieModel) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = movie.name, color = Color.Black)
        Text(text = movie.description, color = Color.Black)
        Text(text = movie.category, color = Color.Black)
        Text(text = movie.director, color = Color.Black)
        Text(text = movie.year.toString(), color = Color.Black)
        Text(text = movie.rating.toString(), color = Color.Black)
        Text(text = movie.price.toString(), color = Color.Black)
    }
}