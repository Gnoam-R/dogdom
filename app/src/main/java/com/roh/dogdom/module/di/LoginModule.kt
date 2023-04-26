package com.roh.dogdom.module.di

import com.roh.dogdom.data.login.LoginRepository
import com.roh.dogdom.data.login.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideLoginRepository(
    ) : LoginRepository {
        return LoginRepositoryImpl()
    }
}