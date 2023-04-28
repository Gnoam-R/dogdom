package com.roh.dogdom.module.di

import com.roh.dogdom.data.bottommenu.BottomMenuRepository
import com.roh.dogdom.data.bottommenu.BottomMenuRepositoryImpl
import com.roh.dogdom.data.permission.PermissionRepository
import com.roh.dogdom.data.permission.PermissionRepositoryImpl
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
    ) : BottomMenuRepository {
        return BottomMenuRepositoryImpl()
    }

}