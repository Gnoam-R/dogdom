package com.roh.dogdom.module.di

import com.roh.dogdom.data.main.MasterMainRepository
import com.roh.dogdom.data.main.MasterMainRepositoryImpl
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
    ) : MasterMainRepository {
        return MasterMainRepositoryImpl()
    }
}