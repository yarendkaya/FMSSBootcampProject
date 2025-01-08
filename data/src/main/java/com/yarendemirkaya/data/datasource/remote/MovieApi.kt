package com.yarendemirkaya.data.datasource.remote

import com.yarendemirkaya.data.datasource.model.response.CartResponse
import com.yarendemirkaya.data.datasource.model.response.MovieCartResponse
import com.yarendemirkaya.data.datasource.model.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieApi {

    @GET("getAllMovies.php")
    suspend fun getAllMovies(): Response<MoviesResponse>

    @POST("insertMovie.php")
    @FormUrlEncoded
    suspend fun insertMovie(
        @Field("name")
        name: String,
        @Field("image")
        image: String,
        @Field("price")
        price: Int,
        @Field("category")
        category: String,
        @Field("rating")
        rating: Double,
        @Field("year")
        year: Int,
        @Field("director")
        director: String,
        @Field("description")
        description: String,
        @Field("orderAmount")
        orderAmount: Int,
        @Field("userName")
        userName: String
    ): Response<CartResponse>

    @POST("deleteMovie.php")
    @FormUrlEncoded
    suspend fun deleteMovie(
        @Field("cartId") cartId: Int,
        @Field("userName") userName: String
    ): Response<CartResponse>

    @POST("getMovieCart.php")
    @FormUrlEncoded
    suspend fun getMovieCart(
        @Field("userName") userName: String
    ): Response<MovieCartResponse>
}