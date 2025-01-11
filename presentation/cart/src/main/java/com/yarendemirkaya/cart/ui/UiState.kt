package com.yarendemirkaya.cart.ui

import com.yarendemirkaya.cart.model.MovieCartUiModel

data class UiState(
    val isLoading: Boolean = false, // ?
    val movies: List<MovieCartUiModel> = emptyList(),
    val error: String? = null,
    val isMovieDeleted: Boolean = false,
)
