package com.yarendemirkaya.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.InsertMovieRequestModel
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val insertMovieUseCase: InsertMovieUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun addMovie(movie: InsertMovieRequestModel) {
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
                        _uiState.update {
                            it.copy(
                                movie = it.movie,
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
    val isLoading: Boolean = false,
    val error: String? = null,
    val movie: MovieModel? = null
)




