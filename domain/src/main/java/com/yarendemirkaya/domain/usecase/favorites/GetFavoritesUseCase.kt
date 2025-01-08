package com.yarendemirkaya.domain.usecase.favorites

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.FavMovieModel
import com.yarendemirkaya.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<ResponseState<List<FavMovieModel>>> {
        return movieRepository.getAllFavoriteMovies()
    }
}