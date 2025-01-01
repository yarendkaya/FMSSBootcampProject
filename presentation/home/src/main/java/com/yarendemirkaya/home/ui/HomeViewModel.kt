package com.yarendemirkaya.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.usecase.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllUsersCase: GetAllMoviesUseCase,
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
                            val newCategories = uiState.categories.apply {
                                addAll(it.data.map { movie -> movie.category })
                            }
                            uiState.copy(
                                movies = it.data,
                                categories = newCategories,
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
    val categories: MutableSet<String> = mutableSetOf("All"), // mutable set kullanarak categories listesinde her elemandan sadece birer tane olmasnı sağlıyoruz.
    val error: String? = null
)