package com.yarendemirkaya.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.model.toFavMovieModel
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
import com.yarendemirkaya.domain.usecase.favorites.CheckIfMovieFavoritedUseCase
import com.yarendemirkaya.domain.usecase.favorites.DeleteFavoriteUseCase
import com.yarendemirkaya.domain.usecase.favorites.InsertFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val insertMovieUseCase: InsertMovieUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val checkIfMovieFavoritedUseCase: CheckIfMovieFavoritedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun addMovieToCart(movie: InsertMovieModel) {
        viewModelScope.launch {
            insertMovieUseCase(movie).collect {
                when (it) {
                    is ResponseState.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is ResponseState.Success -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                insertMovieResponse = it.data
                            )
                        }
                    }

                    is ResponseState.Error -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                error = uiState.error,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }


    fun deleteMovieFromFavorites(movie: MovieModel) {
        viewModelScope.launch {
            deleteFavoriteUseCase.invoke(movie.toFavMovieModel())
            _uiState.update {
                it.copy(
                    isFavorited = false
                )
            }
        }
    }

    fun addMovieToFavorites(movie: MovieModel) {
        viewModelScope.launch {
            insertFavoriteUseCase.invoke(movie.toFavMovieModel())
            _uiState.update {
                it.copy(
                    isFavorited = true
                )
            }
        }
    }

    fun checkIfMovieIsFavorited(name: String) {
        viewModelScope.launch {
            val isFavorited = checkIfMovieFavoritedUseCase.invoke(name)
            _uiState.update {
                it.copy(
                    isFavorited = isFavorited
                )
            }
        }
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFavorited: Boolean = false,
    val insertMovieResponse: CartResponseModel? = null
)




