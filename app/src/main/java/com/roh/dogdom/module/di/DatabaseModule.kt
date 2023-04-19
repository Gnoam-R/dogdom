package com.roh.dogdom.module.di

import android.content.Context
import androidx.room.Room
import com.roh.dogdom.data.db.LogDao
import com.roh.dogdom.data.db.LogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : LogDatabase {
        return Room.databaseBuilder(
            appContext,
            LogDatabase::class.java,
            "logging.db"
        ).build()
    }

    @Provides
    fun provideDao(database: LogDatabase) : LogDao{
        return database.logDao()
    }
}