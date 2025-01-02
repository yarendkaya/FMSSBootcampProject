package com.yarendemirkaya.data.datasource.model.request

import com.yarendemirkaya.domain.model.InsertMovieRequestModel

data class InsertMovieRequest(
    val id: Int,
    val name: String,
    val image: String,
    val price: Int,
    val category: String,
    val rating: Double,
    val year: Int,
    val director: String,
    val description: String,
    val orderAmount: Int,
    val userName: String
)

fun InsertMovieRequest.toDataModel(): InsertMovieRequestModel {
    return InsertMovieRequestModel(
        id = this.id,
        name = this.name,
        image = this.image,
        price = this.price,
        category = this.category,
        rating = this.rating,
        year = this.year,
        director = this.director,
        description = this.description,
        orderAmount = this.orderAmount,
        userName = this.userName
    )
}

fun InsertMovieRequestModel.toDomainModel(): InsertMovieRequest {
    return InsertMovieRequest(
        id = this.id,
        name = this.name,

        image = this.image,

        price = this.price,
        category = this.category,
        rating = this.rating,
        year = this.year,
        director = this.director,
        description = this.description,
        orderAmount = this.orderAmount,
        userName = this.userName
    )
}
