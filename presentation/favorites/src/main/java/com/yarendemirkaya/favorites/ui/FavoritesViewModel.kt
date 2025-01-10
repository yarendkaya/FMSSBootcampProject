package com.yarendemirkaya.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.FavMovieModel
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
import com.yarendemirkaya.domain.usecase.favorites.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val insertMovieUseCase: InsertMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun getFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase().collect {
                when (it) {
                    is ResponseState.Loading -> {
                        _uiState.value = UiState(isLoading = true)
                    }

                    is ResponseState.Success -> {
                        _uiState.value = UiState(favorites = it.data)
                    }

                    is ResponseState.Error -> {
                        _uiState.value = UiState(error = it.message)
                    }
                }
            }
        }
    }

    fun insertMovie(insertMovieModel: InsertMovieModel) {
        viewModelScope.launch {
            insertMovieUseCase(insertMovieModel).collect {
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
                                insertMovieResult = it.data
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
                    else -> {}
                }
            }
        }
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val favorites: List<FavMovieModel> = emptyList(),
    val error: String? = null,
    val insertMovieResult: CartResponseModel? = null
)
