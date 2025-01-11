package com.yarendemirkaya.home.ui

import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieModel

sealed interface UiAction {
    data class OnAddCartClick(val insertMovieModel: InsertMovieModel) : UiAction
    data class OnQueryTextChange(val query: String) : UiAction
    data class OnCategorySelect(val category: String) : UiAction
    data class OnFilterClick(val sortType: SortType) : UiAction
    data class OnMovieClick(val movie: MovieModel) : UiAction
}