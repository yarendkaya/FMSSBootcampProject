package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName

data class MovieCartResponse(
    @SerializedName("movie_cart")
    val movieCart: List<MovieCart>)
