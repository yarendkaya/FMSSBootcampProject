package com.yarendemirkaya.domain.model

data class MovieModel(
    val id: Int,
    val name: String,
    val image: String,
    val price: Int,
    val priceStr: String,
    val category: String,
    val rating: Double,
    val year: Int,
    val director: String,
    val description: String
)

fun MovieModel.toFavMovieModel(): FavMovieModel {
    return FavMovieModel(
        id = this.id,
        name = this.name,
        image = this.image,
        price = this.price,
        category = this.category,
        rating = this.rating,
        year = this.year,
        director = this.director,
        description = this.description
    )
}

fun MovieModel.toInsertMovieModel(): InsertMovieModel {
    return InsertMovieModel(
        name = this.name,
        image = this.image,
        price = this.price,
        category = this.category,
        rating = this.rating,
        year = this.year,
        director = this.director,
        description = this.description,
        orderAmount = 1
    )
}
