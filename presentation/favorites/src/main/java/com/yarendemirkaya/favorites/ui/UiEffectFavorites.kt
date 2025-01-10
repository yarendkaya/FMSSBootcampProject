package com.yarendemirkaya.home.ui

import com.yarendemirkaya.domain.model.MovieModel

sealed interface UiEffectFavorites {
    data object None : UiEffectFavorites
    data class ShowToast(val message: String) : UiEffectFavorites
    data class NavigateToDetailFromFavorites(val movie: MovieModel) : UiEffectFavorites
}