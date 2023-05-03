package com.roh.dogdom.module.di

import com.roh.dogdom.data.messages.MessagesRepository
import com.roh.dogdom.data.messages.MessagesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessagesModule {

    @Singleton
    @Provides
    fun provideMessagesRepository(
    ) : MessagesRepository {
        return MessagesRepositoryImpl()
    }
}