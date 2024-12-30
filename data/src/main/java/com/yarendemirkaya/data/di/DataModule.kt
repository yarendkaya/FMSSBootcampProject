package com.yarendemirkaya.data.di

import android.content.Context
import androidx.room.Room
import com.yarendemirkaya.data.datasource.local.MovieDatabase
import com.yarendemirkaya.data.datasource.remote.MovieApi
import com.yarendemirkaya.data.db.AppDatabase
import com.yarendemirkaya.data.repo.MovieRepoImpl
import com.yarendemirkaya.data.repo.SampleRepoImpl
import com.yarendemirkaya.data.util.Constants.Companion.BASE_URL
import com.yarendemirkaya.domain.repo.MovieRepository
import com.yarendemirkaya.domain.repo.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun provideMovieRepository(
        database: MovieDatabase,
        api: MovieApi
    ): MovieRepository {
        return MovieRepoImpl(database, api)
    }
}