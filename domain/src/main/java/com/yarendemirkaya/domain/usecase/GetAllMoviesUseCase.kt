package com.yarendemirkaya.domain.usecase

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetAllMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<ResponseState<List<MovieModel>>> {
        return movieRepository.getAllMovies()
    }
}
