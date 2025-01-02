package com.yarendemirkaya.detail.di

import com.yarendemirkaya.detail.ui.DetailViewModel
import com.yarendemirkaya.domain.repo.MovieRepository
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DetailUIModule {

    @Provides
    fun provideInsertMovieUseCase(repo: MovieRepository): InsertMovieUseCase {
        return InsertMovieUseCase(repo)
    }


    @Provides
    fun provideDetailViewModel(
        insertMovieUseCase: InsertMovieUseCase
    ): DetailViewModel {
        return DetailViewModel(insertMovieUseCase)
    }
}