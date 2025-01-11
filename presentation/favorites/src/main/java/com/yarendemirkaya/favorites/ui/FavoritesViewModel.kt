package com.yarendemirkaya.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
import com.yarendemirkaya.domain.usecase.favorites.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _uiEffect = MutableSharedFlow<UiEffectFavorites>()
    val uiEffect: SharedFlow<UiEffectFavorites> = _uiEffect.asSharedFlow()


    fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnAddToCartClick -> insertMovie(uiAction.movie)
                is UiAction.OnMovieClick -> _uiEffect.emit(
                    UiEffectFavorites.NavigateToDetailFromFavorites(
                        uiAction.movie
                    )
                )
            }
        }
    }


    fun getFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase().collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        updateUiState {
                            copy(
                                isLoading = false,
                                favorites = response.data,
                                error = null
                            )
                        }
                    }

                    is ResponseState.Error -> {
                        updateUiState {
                            copy(
                                isLoading = false,
                                favorites = emptyList()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun insertMovie(insertMovieModel: InsertMovieModel) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true,
                favorites = emptyList(),)
            when (val response = insertMovieUseCase(insertMovieModel)) {
                is ResponseState.Success -> {
                    updateUiState {
                        copy(
                            isLoading = false
                        )
                    }
                    getFavorites()
//                    _uiEffect.emit(UiEffectFavorites.ShowToast(response.data))
                }

                is ResponseState.Error -> {
                    updateUiState { copy(isLoading = false) }
//                    _uiEffect.emit(UiEffectFavorites.ShowToast(response.message))
                }
            }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }
}




