package com.yarendemirkaya.domain.usecase.favorites

import com.yarendemirkaya.domain.model.FavMovieModel
import com.yarendemirkaya.domain.repo.MovieRepository

class DeleteFavoriteUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(favMovie: FavMovieModel) {
        movieRepository.deleteFavoriteMovie(favMovie)
    }
}