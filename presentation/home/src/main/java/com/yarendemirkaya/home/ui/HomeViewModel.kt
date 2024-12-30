package com.yarendemirkaya.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.model.MoviesResponseModel
import com.yarendemirkaya.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllUsersCase: GetAllUsersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun fetchMovies() {
        viewModelScope.launch {
            getAllUsersCase().collect {
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
                                movies = it.data, // burada eski halinde uida veri gelmiyordu.
                                isLoading = false
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
}

data class UiState(
    val isLoading: Boolean = false, // ?
    val movies: List<MovieModel> = emptyList(),
    val error: String? = null
)