package com.roh.dogdom.module.di

import com.roh.dogdom.data.chatgpt.ChatGptRepository
import com.roh.dogdom.data.chatgpt.ChatGptRepositoryImpl
import com.roh.dogdom.data.dogimage.DogImageRepository
import com.roh.dogdom.data.dogimage.DogImageRepositoryImpl
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