package com.yarendemirkaya.cart.ui

import com.yarendemirkaya.cart.model.MovieCartUiModel

sealed interface UiAction {

    data class OnDeleteClick(val cartId: Int) : UiAction
    data class OnIncreaseQuantityClick(val movie: MovieCartUiModel) : UiAction
    object OnDeleteAllClick : UiAction
    object OnGetCartMovies : UiAction
    data class OnTrashClick(val cartIdList: List<Int>) : UiAction
}