package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.domain.model.MoviesResponseModel


data class MoviesResponse(
    @SerializedName("movies")
    val movies: List<Movie>
)

fun MoviesResponse.toDomainModel() = MoviesResponseModel(
    movies = movies.map { it.toDomainModel() }
)
