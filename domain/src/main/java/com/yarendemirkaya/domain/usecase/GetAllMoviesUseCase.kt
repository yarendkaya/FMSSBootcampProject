package com.yarendemirkaya.domain.usecase

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.repo.MovieRepository
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): ResponseState<List<MovieModel>> {
        return movieRepository.getAllMovies()
    }
}

