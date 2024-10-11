package com.roh.dogdom.module.di

import com.roh.dogdom.data.bottommenu.BottomNavigationRepository
import com.roh.dogdom.data.bottommenu.BottomNavigationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BottomMenuModule {

    @Singleton
    @Provides
    fun provideBottomMenuRepository(
    ) : BottomNavigationRepository {
        return BottomNavigationRepositoryImpl()
    }

}