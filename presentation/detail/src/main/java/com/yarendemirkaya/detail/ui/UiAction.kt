package com.yarendemirkaya.detail.ui

import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieModel

sealed interface UiAction {
    data class OnAddCartClick(val insertMovieModel: InsertMovieModel) : UiAction
    data class OnDeleteFavoriteClick(val movie: MovieModel) : UiAction
    data class OnAddFavoriteClick(val movie: MovieModel) : UiAction
    data class OnCheckIfFavorited(val name: String) : UiAction
}