package com.yarendemirkaya.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.usecase.GetAllMoviesUseCase
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
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
class HomeViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect: SharedFlow<UiEffect> = _uiEffect.asSharedFlow()

    fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnAddCartClick -> insertMovie(uiAction.insertMovieModel)
                is UiAction.OnQueryTextChange -> onSearchTextChange(uiAction.query)
                is UiAction.OnCategorySelect -> filterMoviesByCategory(uiAction.category)
                is UiAction.OnFilterClick -> generalFilter(uiAction.sortType)
                is UiAction.OnMovieClick -> _uiEffect.emit(UiEffect.NavigateToDetail(uiAction.movie))
            }
        }
    }

    private fun onSearchTextChange(query: String) {
        updateUiState { copy(searchQuery = query) }
        filterMoviesBySearchQuery(query)
    }

    private fun filterMoviesBySearchQuery(query: String) {
        viewModelScope.launch {
            val filteredMovies = _uiState.value.movies.filter { it.name.contains(query, ignoreCase = true) }
            updateUiState { copy(filteredMovies = filteredMovies) }
        }
    }

    private fun insertMovie(insertMovieModel: InsertMovieModel) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            when (val response = insertMovieUseCase(insertMovieModel)) {
                is ResponseState.Success -> {
                    updateUiState { copy(isLoading = false) }
//                    _uiEffect.emit(UiEffect.ShowToast(response.data))
                }

                is ResponseState.Error -> {
                    updateUiState { copy(isLoading = false) }
//                    _uiEffect.emit(UiEffect.ShowToast(response.message))
                }

            }
        }
    }

    fun fetchMovies() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            when (val response = getAllMoviesUseCase()) {
                is ResponseState.Success -> {
                    val newCategories = listOf("All") + response.data.map { it.category }.distinct()
                    updateUiState {
                        copy(
                            movies = response.data,
                            filteredMovies = response.data,
                            categories = newCategories,
                            isLoading = false
                        )
                    }
                }
                is ResponseState.Error -> updateUiState { copy(error = response.message, isLoading = false) }
            }
        }
    }

    private fun filterMoviesByCategory(category: String) {
        val allMovies = _uiState.value.movies
        val filteredMovies = if (category == "All") {
            allMovies
        } else {
            allMovies.filter { it.category == category }
        }
        updateUiState {
            copy(selectedCategory = category, filteredMovies = filteredMovies)
        }
    }

    private fun generalFilter(sortType: SortType) {
        val currentState = _uiState.value
        val sortedMovies = if (sortType.ascending) {
            currentState.movies.sortedBy {
                if (sortType.type == SortCategory.PRICE) it.price.toDouble() else it.rating
            }
        } else {
            currentState.movies.sortedByDescending {
                if (sortType.type == SortCategory.PRICE) it.price.toDouble() else it.rating
            }
        }
        updateUiState { copy(filteredMovies = sortedMovies) }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }
}