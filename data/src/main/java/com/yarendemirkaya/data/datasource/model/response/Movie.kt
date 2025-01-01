package com.yarendemirkaya.data.datasource.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.data.db.entity.PersonEntity
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.model.SamplePerson

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("year")
    val year: Int,
    @SerializedName("director")
    val director: String,
    @SerializedName("description")
    val description: String
)

fun Movie.toDomainModel(): MovieModel {
    return MovieModel(
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

fun MovieModel.toDataModel(): Movie {
    return Movie(
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
