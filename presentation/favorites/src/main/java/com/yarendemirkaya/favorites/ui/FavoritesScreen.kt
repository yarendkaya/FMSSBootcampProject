package com.yarendemirkaya.favorites.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.yarendemirkaya.domain.model.toInsertMovieModel
import com.yarendemirkaya.domain.model.toMovieModel
import com.yarendemirkaya.home.ui.components.MovieItem

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel, navController: NavController) {

    LaunchedEffect(key1 = true) {
        viewModel.getFavorites()
    }
    val viewState by viewModel.uiState.collectAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(viewState.favorites.size) {
                MovieItem(
                    movie = viewState.favorites[it].toMovieModel(),
                    onItemClick = {
                        val movieJson =
                            Uri.encode(Gson().toJson(viewState.favorites[it].toMovieModel()))
                        navController.navigate("detail/$movieJson")
                    },
                    onCartClick = {
                        viewModel.insertMovie(
                            viewState.favorites[it].toMovieModel().toInsertMovieModel()
                        )
                    })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Favoriler",
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth(),
    )
}
