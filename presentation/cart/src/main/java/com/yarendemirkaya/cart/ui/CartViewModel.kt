package com.yarendemirkaya.cart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.cart.model.MovieCartUiModel
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.model.MovieCartModel
import com.yarendemirkaya.domain.usecase.DeleteMovieUseCase
import com.yarendemirkaya.domain.usecase.GetCartMoviesUseCase
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
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
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val insertMovieUseCase: InsertMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun deleteMovie(cartId: Int) {
        viewModelScope.launch {
            deleteMovieUseCase(cartId).collect {
                when (it) {
                    is ResponseState.Loading -> {
                        _uiState.update {
                            it.copy(
                                isMovieDeleted = false,
                                isLoading = true
                            )
                        }
                    }

                    is ResponseState.Success -> {
                        _uiState.update {
                            it.copy(
                                isMovieDeleted = true,
                                isLoading = false
                            )
                        }
                        handleLastItemDelete()
                        getCartMovies()
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

    private fun handleLastItemDelete() {
        if (_uiState.value.movies.size == 1 && _uiState.value.isMovieDeleted && _uiState.value.movies.first().orderAmount == 1) {
            _uiState.update {
                it.copy(
                    movies = emptyList()
                )
            }
        }
    }


    fun getCartMovies() {
        viewModelScope.launch {
            getCartMoviesUseCase().collect {
                when (it) {
                    is ResponseState.Loading -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                isLoading = true
                            )
                        }
                    }

                    is ResponseState.Success -> {
                        val moviesWithOrderAmount = createMovieCartUiModels(it.data)
                        _uiState.update { uiState ->
                            uiState.copy(
                                movies = moviesWithOrderAmount,
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

    private fun createMovieCartUiModels(movieCartModelList: List<MovieCartModel>): List<MovieCartUiModel> {
        val moviesWithOrderAmount = mutableListOf<MovieCartUiModel>()
        val groupedMovies = movieCartModelList.groupBy { it.name }

        groupedMovies.forEach { (name, movies) ->
            val cartIdList = mutableListOf<Int>()

            movies.forEach { cartMovie ->
                cartIdList.add(cartMovie.cartId)
            }
            moviesWithOrderAmount.add(
                MovieCartUiModel(
                    cartIdList = cartIdList,
                    name = name,
                    image = movies[0].image,
                    price = movies[0].price,
                    category = movies[0].category,
                    rating = movies[0].rating,
                    year = movies[0].year,
                    director = movies[0].director,
                    description = movies[0].description,
                    orderAmount = cartIdList.size
                )
            )
        }
        return moviesWithOrderAmount.toList()
    }

    fun increaseQuantity(movie: MovieCartUiModel) {
        viewModelScope.launch {
            insertMovieUseCase(
                InsertMovieModel(
                    name = movie.name,
                    image = movie.image,
                    price = movie.price,
                    category = movie.category,
                    rating = movie.rating,
                    year = movie.year,
                    director = movie.director,
                    description = movie.description,
                    orderAmount = 1
                )
            ).collect {
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
                                isLoading = false
                            )
                        }
                        getCartMovies()
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
    val movies: List<MovieCartUiModel> = emptyList(),
    val error: String? = null,
    val isMovieDeleted: Boolean = false
)
