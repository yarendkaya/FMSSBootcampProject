package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.domain.model.MovieCartResponseModel

data class MovieCartResponse(
    @SerializedName("movie_cart")
    val movieCart: List<MovieCart>
)

fun MovieCartResponse.toDomainModel(): MovieCartResponseModel {
    return MovieCartResponseModel(
        movieCart = movieCart.map { it.toDomainModel() }
    )
}

fun MovieCartResponseModel.toDataModel(): MovieCartResponse {
    return MovieCartResponse(
        movieCart = movieCart.map { it.toDataModel() }
    )
}

