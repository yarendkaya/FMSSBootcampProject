package com.yarendemirkaya.sample.di

import com.yarendemirkaya.domain.usecase.GetUserUseCase
import com.yarendemirkaya.domain.repo.SampleRepository
import com.yarendemirkaya.domain.usecase.SavePersonUseCase
import com.yarendemirkaya.sample.ui.SampleViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SampleModule {

    @Provides
    fun provideGetUserUseCase(repo: SampleRepository): GetUserUseCase {
        return GetUserUseCase(repo)
    }

    @Provides
    fun provideSavePersonUseCase(repo: SampleRepository): SavePersonUseCase {
        return SavePersonUseCase(repo)
    }

    @Provides
    fun provideSampleViewModel(
        getUserUseCase: GetUserUseCase,
        savePersonUseCase: SavePersonUseCase
    ): SampleViewModel {
        return SampleViewModel(getUserUseCase, savePersonUseCase)
    }

}