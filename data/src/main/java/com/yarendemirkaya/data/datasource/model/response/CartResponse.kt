package com.yarendemirkaya.data.datasource.model.response

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String)
