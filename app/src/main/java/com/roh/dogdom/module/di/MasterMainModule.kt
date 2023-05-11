package com.roh.dogdom.module.di

import com.roh.dogdom.data.home.HomeRepository
import com.roh.dogdom.data.home.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MasterMainModule {

    @Singleton
    @Provides
    fun provideMasterMainRepository(
    ) : HomeRepository {
        return HomeRepositoryImpl()
    }
}