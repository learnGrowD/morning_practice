package com.will_d.ex32hilt.di

import com.will_d.ex32hilt.repository.Repository
import com.will_d.ex32hilt.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl) : Repository

}