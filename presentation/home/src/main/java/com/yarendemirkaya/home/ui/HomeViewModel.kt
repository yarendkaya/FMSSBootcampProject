package com.yarendemirkaya.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.usecase.GetAllMoviesUseCase
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
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
    private val insertMovieUseCase: InsertMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun onSearchTextChange(query: String) {
        _uiState.update { uiState ->
            uiState.copy(searchQuery = query)
        }
        filterMoviesBySearchQuery(query)
    }

    private fun filterMoviesBySearchQuery(query: String) {
        viewModelScope.launch {
            getAllUsersCase().collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        val allMovies = response.data
                        val filteredMovies = if (query.isEmpty()) {
                            allMovies
                        } else {
                            allMovies.filter { it.name.contains(query, ignoreCase = true) }
                        }
                        _uiState.update { uiState ->
                            uiState.copy(movies = filteredMovies)
                        }
                    }
                    else -> {
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

    fun fetchMovies(
//        category: String
    ) {
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

//                            val filteredMovies = if (category == "All") {
//                                it.data
//                            } else {
//                                it.data.filter { movie -> movie.category == category }
//                            }

                            uiState.copy(
                                movies =
//                                filteredMovies
                                it.data,
                                categories = newCategories,
                                isLoading = false,
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

    fun filterMoviesByCategory(category: String) {
        val allMovies = _uiState.value.movies
        _uiState.value = _uiState.value.copy(
            movies = allMovies.filter { it.category == category }
        )
    }

    fun generalFilter(sortBy: String, ascending: Boolean) {
        val sortedMovies = when (sortBy) {
            "price" -> if (ascending) {
                _uiState.value.movies.sortedBy { it.price }
            } else {
                _uiState.value.movies.sortedByDescending { it.price }
            }

            "rating" -> if (ascending) {
                _uiState.value.movies.sortedBy { it.rating }
            } else {
                _uiState.value.movies.sortedByDescending { it.rating }
            }

            else -> _uiState.value.movies
        }
        _uiState.value = _uiState.value.copy(movies = sortedMovies)
    }
}

data class UiState(
    val isLoading: Boolean = false, // ?
    val movies: List<MovieModel> = emptyList(),
    val categories: MutableSet<String> = mutableSetOf("All"), // mutable set kullanarak categories listesinde her elemandan sadece birer tane olmasnı sağlıyoruz.
    val error: String? = null,
    val selectedCategory: String = "All",
    val insertMovieResult: CartResponseModel? = null,
    val searchQuery: String = ""
)