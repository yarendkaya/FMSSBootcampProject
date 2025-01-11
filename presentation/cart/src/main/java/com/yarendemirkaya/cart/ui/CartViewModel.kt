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
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnDeleteClick -> deleteMovie(uiAction.cartId)
            is UiAction.OnIncreaseQuantityClick -> increaseQuantity(uiAction.movie)
            is UiAction.OnDeleteAllClick -> deleteAllMovies()
            is UiAction.OnGetCartMovies -> getCartMovies()
            is UiAction.OnTrashClick -> deleteMovieList(uiAction.cartIdList)
        }
    }

    private fun deleteMovie(cartId: Int) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            when (val response = deleteMovieUseCase(cartId)) {
                is ResponseState.Success -> {
                    updateUiState {
                        copy(isMovieDeleted = true)
                    }
                    handleLastItemDelete()
                    getCartMovies()
                }

                is ResponseState.Error -> {
                    updateUiState {
                        copy(error = response.message)
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
            updateUiState {
                copy(isLoading = true)
            }
            when (val response = getCartMoviesUseCase()) {
                is ResponseState.Success -> {
                    val moviesWithOrderAmount = createMovieCartUiModels(response.data)
                    updateUiState {
                        copy(
                            movies = moviesWithOrderAmount,
                            isLoading = false
                        )
                    }
                }

                is ResponseState.Error -> {
                    updateUiState {
                        copy(
                            error = error,
                            isLoading = false
                        )
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

    private fun increaseQuantity(movie: MovieCartUiModel) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val insertMovieModel = InsertMovieModel(
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
            when (val response = insertMovieUseCase(insertMovieModel)) {
                is ResponseState.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = it.movies.map { movie ->
                                if (movie.name == response.data) {
                                    movie.copy(orderAmount = movie.orderAmount + 1)
                                } else {
                                    movie
                                }
                            }
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

    private fun deleteMovieList(cartIdList: List<Int>) {
        viewModelScope.launch {
            val jobs = cartIdList.map { cartId ->
                async {
                    deleteMovie(cartId)
                }
            }
            jobs.awaitAll()
            updateUiState {
                copy(
                    movies = emptyList()
                )
            }
        }
    }


    private fun deleteAllMovies() {
        viewModelScope.launch {
            val jobs = uiState.value.movies.map { movie ->
                async {
                    movie.cartIdList.forEach { cartId ->
                        deleteMovie(cartId)
                    }
                }
            }
            jobs.awaitAll()
            updateUiState {
                copy(
                    movies = emptyList()
                )
            }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }
}
