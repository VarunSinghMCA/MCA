package com.example.unisync.di

import com.example.unisync.data.repository.UniversityRepository
import com.example.unisync.data.repository.UniversityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUniversityRepository(
        repositoryImpl: UniversityRepositoryImpl
    ): UniversityRepository
}
