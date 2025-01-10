package com.yarendemirkaya.domain.usecase.favorites

import com.yarendemirkaya.domain.repo.MovieRepository

class CheckIfMovieFavoritedUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(name: String): Boolean {
        return movieRepository.checkIfMovieIsFavorited(name)
    }
}