package com.roh.dogdom.module.di

import com.roh.dogdom.data.image.remote.DogImageRepository
import com.roh.dogdom.data.image.remote.DogImageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogImageModule {

    @Singleton
    @Provides
    fun provideDogImageRepository(
    ) : DogImageRepository {
        return DogImageRepositoryImpl()
    }
}