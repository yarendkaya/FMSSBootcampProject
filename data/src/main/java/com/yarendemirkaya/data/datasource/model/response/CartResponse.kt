package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.domain.model.CartResponseModel

data class CartResponse(
    @SerializedName("success")
    val success: Int? = null,
    @SerializedName("message")
    val message: String? = null
)

fun CartResponse?.toDomainModel() = CartResponseModel(
    success = this?.success ?: 0,
    message = this?.message.orEmpty(),
)

