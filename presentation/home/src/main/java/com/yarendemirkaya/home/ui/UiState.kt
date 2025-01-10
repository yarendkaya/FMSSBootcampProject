package com.yarendemirkaya.home.ui

import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.MovieModel

data class UiState(
    val isLoading: Boolean = false,
    val movies: List<MovieModel> = emptyList(),
    val categories: List<String> = emptyList(),
    val error: String? = null,
    val selectedCategory: String = "All",
    val insertMovieResult: CartResponseModel? = null,
    val searchQuery: String = "",
    val filteredMovies: List<MovieModel> = emptyList()
)