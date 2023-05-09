package com.roh.dogdom.module.di

import com.roh.dogdom.data.main.HomeRepository
import com.roh.dogdom.data.main.HomeRepositoryImpl
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