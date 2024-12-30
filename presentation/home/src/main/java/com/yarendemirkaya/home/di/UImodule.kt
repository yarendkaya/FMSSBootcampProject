package com.yarendemirkaya.home.di

import com.yarendemirkaya.domain.repo.MovieRepository
import com.yarendemirkaya.domain.usecase.GetAllUsersUseCase
import com.yarendemirkaya.home.ui.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UImodule {

    @Provides
    fun provideGetAllUsersUseCase(repo: MovieRepository): GetAllUsersUseCase {
        return GetAllUsersUseCase(repo)
    }


    @Provides
    fun provideHomeViewModel(
        getAllUsersCase: GetAllUsersUseCase,
    ): HomeViewModel {
        return HomeViewModel(getAllUsersCase)
    }

}