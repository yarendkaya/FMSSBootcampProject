package com.yarendemirkaya.data.datasource.model.response

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.domain.model.MovieModel

data class Movie(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("year")
    val year: Int? = null,
    @SerializedName("director")
    val director: String? = null,
    @SerializedName("description")
    val description: String? = null
)

fun List<Movie>?.toDomainModel(): List<MovieModel> {
    return this?.map {
        MovieModel(
            id = it.id ?: 0,
            name = it.name.orEmpty(),
            image = "http://kasimadalan.pe.hu/movies/images/${it.image}",
            price = it.price ?: 0,
            priceStr = "${it.price} ₺",
            category = it.category.orEmpty(),
            rating = it.rating ?: 0.0,
            year = it.year ?: 0,
            director = it.director.orEmpty(),
            description = it.description.orEmpty(),
        )
    }.orEmpty()
}

