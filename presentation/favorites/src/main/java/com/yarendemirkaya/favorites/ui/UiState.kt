package com.yarendemirkaya.favorites.ui

import com.yarendemirkaya.domain.model.FavMovieModel

data class UiState(
    val isLoading: Boolean = false,
    val favorites: List<FavMovieModel> = emptyList(),
    val error: String? = null,
)