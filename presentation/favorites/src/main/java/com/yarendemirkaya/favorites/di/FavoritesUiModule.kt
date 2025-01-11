package com.yarendemirkaya.favorites.di

import com.yarendemirkaya.domain.repo.MovieRepository
import com.yarendemirkaya.domain.usecase.favorites.CheckIfMovieFavoritedUseCase
import com.yarendemirkaya.domain.usecase.favorites.DeleteFavoriteUseCase
import com.yarendemirkaya.domain.usecase.favorites.GetFavoritesUseCase
import com.yarendemirkaya.domain.usecase.favorites.InsertFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object FavoritesUiModule {

    @Provides
    fun provideGetFavoritesUseCase(movieRepository: MovieRepository): GetFavoritesUseCase {
        return GetFavoritesUseCase(movieRepository)
    }

    @Provides
    fun provideDeleteFavoriteUseCase(movieRepository: MovieRepository): DeleteFavoriteUseCase {
        return DeleteFavoriteUseCase(movieRepository)
    }

    @Provides
    fun provideInsertFavoriteUseCase(movieRepository: MovieRepository): InsertFavoriteUseCase {
        return InsertFavoriteUseCase(movieRepository)
    }

    @Provides
    fun provideCheckIfMovieFavoritedUseCase(movieRepository: MovieRepository): CheckIfMovieFavoritedUseCase {
        return CheckIfMovieFavoritedUseCase(movieRepository)
    }
}