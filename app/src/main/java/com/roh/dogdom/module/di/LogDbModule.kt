package com.roh.dogdom.module.di

import android.content.Context
import androidx.room.Room
import com.roh.dogdom.data.db.log.LogDao
import com.roh.dogdom.data.db.log.LogDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object LogDbModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : LogDB {
        return Room.databaseBuilder(
            appContext,
            LogDB::class.java,
            "logs.db"
        ).build()
    }

    @Provides
    fun provideDao(database: LogDB) : LogDao {
        return database.logDao()
    }
}