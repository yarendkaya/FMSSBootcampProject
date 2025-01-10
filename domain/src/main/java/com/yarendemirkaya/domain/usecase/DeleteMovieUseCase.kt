package com.yarendemirkaya.domain.usecase

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.CartResponseModel
import com.yarendemirkaya.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

class DeleteMovieUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(cartId: Int, ): Flow<ResponseState<CartResponseModel>> {
        return movieRepository.deleteMovie(cartId)
    }
}