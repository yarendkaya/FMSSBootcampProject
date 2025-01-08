package com.yarendemirkaya.domain.usecase

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.MovieCartModel
import com.yarendemirkaya.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetCartMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<ResponseState<List<MovieCartModel>>> {
        return movieRepository.getMovieCart()
    }
}