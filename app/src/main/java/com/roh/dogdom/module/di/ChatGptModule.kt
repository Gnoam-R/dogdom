package com.roh.dogdom.module.di

import com.roh.dogdom.data.chatgpt.ChatGptRepository
import com.roh.dogdom.data.chatgpt.ChatGptRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatGptModule {

    @Singleton
    @Provides
    fun provideChatGptRepository(
    ) : ChatGptRepository {
        return ChatGptRepositoryImpl()
    }
}