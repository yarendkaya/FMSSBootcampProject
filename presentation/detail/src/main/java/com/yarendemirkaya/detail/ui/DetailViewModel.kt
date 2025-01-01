package com.yarendemirkaya.detail.ui

import androidx.lifecycle.ViewModel
import com.yarendemirkaya.domain.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//@HiltViewModel
class DetailViewModel() : ViewModel() {

    private val _movieDetail = MutableStateFlow(MovieDetailState())
    val movieDetail: StateFlow<MovieDetailState> = _movieDetail

}


data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieModel? = null,
    val error: String? = null
)

