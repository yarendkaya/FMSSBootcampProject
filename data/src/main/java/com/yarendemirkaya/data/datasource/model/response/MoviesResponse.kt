package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.domain.model.MoviesResponseModel

data class MoviesResponse(
    @SerializedName("movies")
    val movies: List<Movie>? = null,
    @SerializedName("success")
    val success: Int? = null,
    @SerializedName("message")
    val message: String? = null,
)
