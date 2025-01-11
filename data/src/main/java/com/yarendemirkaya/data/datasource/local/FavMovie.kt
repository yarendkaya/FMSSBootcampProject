package com.yarendemirkaya.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yarendemirkaya.domain.model.FavMovieModel

@Entity("movie_table")
data class FavMovie(
    @PrimaryKey(autoGenerate = true)
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

fun FavMovie.toDomainModel(): FavMovieModel {
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

fun FavMovieModel.toDataModel(): FavMovie {
    return FavMovie(
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