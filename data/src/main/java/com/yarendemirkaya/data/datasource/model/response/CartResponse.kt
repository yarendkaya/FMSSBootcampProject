package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.domain.model.CartResponseModel

data class CartResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String
)

fun CartResponse.toDomainModel() = CartResponseModel(
    success = success,
    message = message
)

