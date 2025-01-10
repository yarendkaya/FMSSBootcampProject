package com.yarendemirkaya.home.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yarendemirkaya.base.ui.LoadingIndicator
import com.yarendemirkaya.base.ui.collectWithLifecycle
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.home.ui.components.Categories
import com.yarendemirkaya.home.ui.components.CustomSearchBar
import com.yarendemirkaya.home.ui.components.CustomTopAppBar
import com.yarendemirkaya.home.ui.components.MovieGrid
import kotlinx.coroutines.flow.Flow


@Composable
fun HomeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetail: (MovieModel) -> Unit,
) {
    val context = LocalContext.current

    uiEffect.collectWithLifecycle {
        when (it) {
            is UiEffect.ShowToast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            is UiEffect.NavigateToDetail -> onNavigateToDetail(it.movie)
            is UiEffect.None -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(4.dp)
    ) {
        CustomTopAppBar()
        CustomSearchBar(onSearchQueryChange = { onAction(UiAction.OnQueryTextChange(it)) })

        if (uiState.categories.isNotEmpty()) {
            Categories(
                categories = uiState.categories,
                onCategorySelected = { onAction(UiAction.OnCategorySelect(it)) },
                onFilterClick = { onAction(UiAction.OnFilterClick(it)) }
            )
        }

        if (uiState.movies.isNotEmpty()) {
            MovieGrid(
                movies = uiState.filteredMovies,
                onMovieClick = { onAction(UiAction.OnMovieClick(it)) },
                onCartClick = { onAction(UiAction.OnAddCartClick(it)) }
            )
        }
    }

    if (uiState.isLoading) LoadingIndicator()
}













