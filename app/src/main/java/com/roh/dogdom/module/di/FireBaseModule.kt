package com.roh.dogdom.module.di

import com.roh.dogdom.data.dogimage.DogImageRepository
import com.roh.dogdom.data.dogimage.DogImageRepositoryImpl
import com.roh.dogdom.data.firebase.FirebaseRepository
import com.roh.dogdom.data.firebase.FirebaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FireBaseModule {

    @Singleton
    @Provides
    fun provideDogImageRepository(
    ) : FirebaseRepository {
        return FirebaseRepositoryImpl()
    }
}