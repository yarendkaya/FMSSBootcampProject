package com.yarendemirkaya.home.di

import com.yarendemirkaya.domain.repo.MovieRepository
import com.yarendemirkaya.domain.usecase.GetAllMoviesUseCase
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
import com.yarendemirkaya.home.ui.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


//@Module
//@InstallIn(ViewModelComponent::class)
//object HomeUIModule {
//
//    @Provides
//    fun provideGetAllUsersUseCase(repo: MovieRepository): GetAllMoviesUseCase {
//        return GetAllMoviesUseCase(repo)
//    }
//
//
//    @Provides
//    fun provideInsertMovieUseCase(repo: MovieRepository): InsertMovieUseCase {
//        return InsertMovieUseCase(repo)
//    }
//
//    @Provides
//    fun provideHomeViewModel(
//        getAllUsersCase: GetAllMoviesUseCase,
//        insertMovieUseCase: InsertMovieUseCase
//    ): HomeViewModel {
//        return HomeViewModel(getAllUsersCase, insertMovieUseCase)
//    }
//}