package com.yarendemirkaya.domain.usecase

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.model.InsertMovieRequestModel
import com.yarendemirkaya.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

class InsertMovieUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieInsert: InsertMovieRequestModel): Flow<ResponseState<CartResponseModel>> {
        return movieRepository.insertMovie(movieInsert)
    }
}