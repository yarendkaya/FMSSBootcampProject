package com.yarendemirkaya.favorites.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yarendemirkaya.base.ui.LoadingIndicator
import com.yarendemirkaya.base.ui.MovieItem
import com.yarendemirkaya.base.ui.collectWithLifecycle
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.model.toInsertMovieModel
import com.yarendemirkaya.domain.model.toMovieModel
import com.yarendemirkaya.favorites.R
import kotlinx.coroutines.flow.Flow

@Composable
fun FavoritesScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffectFavorites>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetailFromFavorites: (MovieModel) -> Unit,
) {
    val context = LocalContext.current

    uiEffect.collectWithLifecycle {
        when (it) {
            is UiEffectFavorites.ShowToast -> Toast.makeText(
                context,
                it.message,
                Toast.LENGTH_SHORT
            ).show()

            is UiEffectFavorites.NavigateToDetailFromFavorites -> onNavigateToDetailFromFavorites(it.movie)
            is UiEffectFavorites.None -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        CustomTopAppBar()
        if (uiState.favorites.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(uiState.favorites.size) {
                    MovieItem(
                        movie = uiState.favorites[it].toMovieModel(),
                        onItemClick = {
                            onAction(UiAction.OnMovieClick(uiState.favorites[it].toMovieModel()))
                        },
                        onCartClick = {
                            onAction(UiAction.OnAddToCartClick(uiState.favorites[it].toInsertMovieModel()))
                        })
                }
            }
        }
        if (uiState.isLoading) LoadingIndicator()
    }
}

@Composable
fun CustomTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.favorites_topBar),
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

