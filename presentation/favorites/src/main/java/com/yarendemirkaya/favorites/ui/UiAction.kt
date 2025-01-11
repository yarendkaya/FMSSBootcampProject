package com.yarendemirkaya.favorites.ui

import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieModel

sealed interface UiAction {
    data class OnAddToCartClick(val movie: InsertMovieModel) : UiAction
    data class OnMovieClick(val movie: MovieModel) : UiAction
}