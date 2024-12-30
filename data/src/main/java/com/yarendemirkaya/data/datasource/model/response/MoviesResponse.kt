package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @SerializedName("movies")
    val movies: List<Movie>
)
