package com.roh.dogdom.module.di

import android.content.Context
import androidx.room.Room
import com.roh.dogdom.data.db.BaseDao
import com.roh.dogdom.data.db.BaseDb
import com.roh.dogdom.data.db.Log.LogDao
import com.roh.dogdom.data.db.Log.LogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object BaseDbModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : BaseDb {
        return Room.databaseBuilder(
            appContext,
            BaseDb::class.java,
            "base.db"
        ).build()
    }

    @Provides
    fun provideDao(database: BaseDb) : BaseDao {
        return database.baseDao()
    }
}