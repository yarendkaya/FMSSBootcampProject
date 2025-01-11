package com.yarendemirkaya.home.ui

import com.yarendemirkaya.domain.model.MovieModel

sealed interface UiEffect {
    data object None : UiEffect
    data class ShowToast(val message: String) : UiEffect
    data class NavigateToDetail(val movie: MovieModel) : UiEffect
}