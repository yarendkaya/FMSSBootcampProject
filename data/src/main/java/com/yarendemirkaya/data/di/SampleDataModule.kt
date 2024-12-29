package com.yarendemirkaya.data.di

import android.content.Context
import androidx.room.Room
import com.yarendemirkaya.data.db.AppDatabase
import com.yarendemirkaya.data.db.PersonDao
import com.yarendemirkaya.data.repo.SampleRepoImpl
import com.yarendemirkaya.domain.repo.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Singleton olarak sağlanır
object SampleDataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "blossom_database"
        ).build()
    }

    @Provides
    fun providePersonDao(appDatabase: AppDatabase): PersonDao {
        return appDatabase.personDao() // Room veritabanı sağlanması gerekiyor
    }

    @Provides
    fun provideSampleRepository(persondb: AppDatabase): SampleRepository {
        return SampleRepoImpl(persondb)
    }
}