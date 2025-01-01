package com.yarendemirkaya.domain.repo

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getAllMovies(): Flow<ResponseState<List<MovieModel>>>
}