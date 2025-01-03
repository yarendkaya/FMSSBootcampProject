package com.yarendemirkaya.cart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.DeleteMovieRequestModel
import com.yarendemirkaya.domain.model.MovieCartModel
import com.yarendemirkaya.domain.model.MovieCartResponseModel
import com.yarendemirkaya.domain.usecase.DeleteMovieUseCase
import com.yarendemirkaya.domain.usecase.GetCartMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartMoviesUseCase: GetCartMoviesUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun deleteMovie(deleteMovieRequest: DeleteMovieRequestModel) {
        viewModelScope.launch {
            deleteMovieUseCase(deleteMovieRequest).collect {
                when (it) {
                    is ResponseState.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is ResponseState.Success -> {
                        _uiState.update {
                            it.copy(
                                deletedMovie = it.deletedMovie,
                                isLoading = false
                            )
                        }
                    }

                    is ResponseState.Error -> {
                        _uiState.update {
                            it.copy(
                                error = it.error,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }


    fun getCartMovies(userName: String) {
        viewModelScope.launch {
            getCartMoviesUseCase(userName).collect {
                when (it) {
                    is ResponseState.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is ResponseState.Success -> {
                        _uiState.update {
                            it.copy(
                                movies = it.movies,
                                isLoading = false
                            )
                        }
                    }

                    is ResponseState.Error -> {
                        _uiState.update {
                            it.copy(
                                error = it.error,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}

data class UiState(
    val isLoading: Boolean = false, // ?
    val movies: List<MovieCartModel>? = null,
    val deletedMovie: DeleteMovieRequestModel? = null,
    val error: String? = null
)