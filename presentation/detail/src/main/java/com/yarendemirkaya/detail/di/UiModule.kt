package com.yarendemirkaya.detail.di

import com.yarendemirkaya.detail.ui.DetailViewModel
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
import com.yarendemirkaya.domain.usecase.favorites.CheckIfMovieFavoritedUseCase
import com.yarendemirkaya.domain.usecase.favorites.DeleteFavoriteUseCase
import com.yarendemirkaya.domain.usecase.favorites.InsertFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DetailUIModule {


    @Provides
    fun provideDetailViewModel(
        insertMovieUseCase: InsertMovieUseCase,
        insertFavoriteUseCase: InsertFavoriteUseCase,
        deleteFavoriteUseCase: DeleteFavoriteUseCase,
        checkIfMovieFavoritedUseCase: CheckIfMovieFavoritedUseCase
    ): DetailViewModel {
        return DetailViewModel(
            insertMovieUseCase,
            insertFavoriteUseCase,
            deleteFavoriteUseCase,
            checkIfMovieFavoritedUseCase
        )
    }
}