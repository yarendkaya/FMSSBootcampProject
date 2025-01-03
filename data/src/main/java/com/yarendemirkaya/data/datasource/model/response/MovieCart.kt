package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.domain.model.MovieCartModel

data class MovieCart(
    @SerializedName("cartId")
    val cartId: Int,
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
    val description: String,
    @SerializedName("orderAmount")
    val orderAmount: Int,
    @SerializedName("userName")
    val userName: String
)

fun MovieCart.toDomainModel(): MovieCartModel {
    return MovieCartModel(
        cartId = this.cartId,
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

fun MovieCartModel.toDataModel(): MovieCart {
    return MovieCart(
        cartId = this.cartId,
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

