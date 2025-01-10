package com.yarendemirkaya.cart.model

import com.yarendemirkaya.domain.model.MovieModel

data class MovieCartUiModel(
    val cartIdList: MutableList<Int>,
    val name: String,
    val image: String,
    val price: Int,
    val category: String,
    val rating: Double,
    val year: Int,
    val director: String,
    val description: String,
    var orderAmount: Int,
)

fun MovieCartUiModel.toMovieModel() = MovieModel(
    id = cartIdList[0],
    name = name,
    image = image,
    price = price,
    category = category,
    rating = rating,
    year = year,
    director = director,
    description = description)