package com.roh.dogdom.module.di
import com.roh.dogdom.data.login.kakao.KakaoLoginRepository
import com.roh.dogdom.data.login.kakao.KakaoLoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object KakaoLoginModule {

    @Singleton
    @Provides
    fun provideLoginRepository(
    ) : KakaoLoginRepository {
        return KakaoLoginRepositoryImpl()
    }
}