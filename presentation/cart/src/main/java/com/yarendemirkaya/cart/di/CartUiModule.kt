package com.yarendemirkaya.cart.di

import com.yarendemirkaya.cart.ui.CartViewModel
import com.yarendemirkaya.domain.repo.MovieRepository
import com.yarendemirkaya.domain.usecase.DeleteMovieUseCase
import com.yarendemirkaya.domain.usecase.GetCartMoviesUseCase
import com.yarendemirkaya.domain.usecase.InsertMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object CartUiModule {

    @Provides
    fun getCartMoviesUseCase(repo: MovieRepository): GetCartMoviesUseCase {
        return GetCartMoviesUseCase(repo)
    }

    @Provides
    fun provideDeleteMovieUseCase(repo: MovieRepository): DeleteMovieUseCase {
        return DeleteMovieUseCase(repo)
    }

    @Provides
    fun provideCartViewModel(
        getCartMoviesUseCase: GetCartMoviesUseCase, deleteMovieUseCase: DeleteMovieUseCase,insertMovieUseCase: InsertMovieUseCase
    ): CartViewModel {
        return CartViewModel(getCartMoviesUseCase, deleteMovieUseCase, insertMovieUseCase)
    }
}