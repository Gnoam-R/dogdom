package com.roh.dogdom.module.di

import com.roh.dogdom.data.db.user.UserLocalDataSource
import com.roh.dogdom.data.firebase.user.UserRepository
import com.roh.dogdom.data.firebase.user.UserRepositoryImpl
import com.roh.dogdom.data.login.LoginRepository
import com.roh.dogdom.data.login.LoginRepositoryImpl
import com.roh.dogdom.data.login.google.GoogleLoginRepository
import com.roh.dogdom.data.login.google.GoogleLoginRepositoryImpl
import com.roh.dogdom.data.login.kakao.KakaoLoginRepository
import com.roh.dogdom.data.login.kakao.KakaoLoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Singleton
    @Provides
    fun provideLoginRepository(
    ) : LoginRepository {
        return LoginRepositoryImpl(
            GoogleLoginRepositoryImpl(),
            KakaoLoginRepositoryImpl(),
            UserRepositoryImpl(),
        )
    }
}