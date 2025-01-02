package com.yarendemirkaya.data.datasource.remote

import com.yarendemirkaya.data.datasource.model.response.CartResponse
import com.yarendemirkaya.data.datasource.model.response.MovieCartResponse
import com.yarendemirkaya.data.datasource.model.response.MoviesResponse
import com.yarendemirkaya.data.datasource.model.request.DeleteMovieRequest
import com.yarendemirkaya.data.datasource.model.request.InsertMovieRequest
import com.yarendemirkaya.domain.model.InsertMovieRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieApi {

    @GET("getAllMovies.php")
    suspend fun getAllMovies(): Response<MoviesResponse>

    @POST("insertMovie.php")
    suspend fun insertMovie(
        @Body insertMovieRequest: InsertMovieRequestModel
    ): Response<CartResponse>

    @POST("deleteMovie.php")
    suspend fun deleteMovie(
       @Body deleteMovieRequest: DeleteMovieRequest
    ): Response<CartResponse>

    @POST
    suspend fun getMovieCart(
        @Field("userName") userName: String
    ): Response<MovieCartResponse>

}