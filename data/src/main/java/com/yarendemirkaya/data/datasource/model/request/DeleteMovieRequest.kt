package com.yarendemirkaya.data.datasource.model.request

import com.google.gson.annotations.SerializedName
import com.yarendemirkaya.domain.model.DeleteMovieRequestModel

data class DeleteMovieRequest(
    @SerializedName("cartId")
    val cartId: Int,
    @SerializedName("userName")
    val userName: String
)

fun DeleteMovieRequest.toDomainModel(): DeleteMovieRequestModel {
    return DeleteMovieRequestModel(
        cartId = this.cartId,
        userName = this.userName
    )
}

fun DeleteMovieRequestModel.toDataModel(): DeleteMovieRequest {
    return DeleteMovieRequest(
        cartId = this.cartId,
        userName = this.userName)
}