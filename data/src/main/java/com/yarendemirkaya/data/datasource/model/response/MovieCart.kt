package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName

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
