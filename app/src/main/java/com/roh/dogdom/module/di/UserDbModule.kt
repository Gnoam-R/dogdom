package com.roh.dogdom.module.di

import android.content.Context
import androidx.room.Room
import com.roh.dogdom.data.db.user.UserDB
import com.roh.dogdom.data.db.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UserDbModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : UserDB {
        return Room.databaseBuilder(
            appContext,
            UserDB::class.java,
            "user.db")
            .build()
    }

    @Provides
    fun provideDao(database: UserDB) : UserDao {
        return database.userDao()
    }
}