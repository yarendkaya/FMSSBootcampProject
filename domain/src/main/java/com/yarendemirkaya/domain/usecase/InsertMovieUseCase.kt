package com.yarendemirkaya.domain.usecase

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.InsertMovieModel
import com.yarendemirkaya.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(movieInsert: InsertMovieModel): ResponseState<String> {
        return movieRepository.insertMovie(movieInsert)
    }
}