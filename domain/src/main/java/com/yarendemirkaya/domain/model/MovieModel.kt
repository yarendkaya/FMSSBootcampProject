package com.yarendemirkaya.domain.model

data class MovieModel(
    val id: Int,
    val name: String,
    val image: String,
    val price: Int,
    val category: String,
    val rating: Double,
    val year: Int,
    val director: String,
    val description: String
)
