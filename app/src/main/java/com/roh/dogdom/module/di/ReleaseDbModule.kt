package com.roh.dogdom.module.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.roh.dogdom.data.db.release.local.ReleaseDao
import com.roh.dogdom.data.db.release.local.ReleaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ReleaseDbModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : ReleaseDatabase {
        return Room.databaseBuilder(
            appContext,
            ReleaseDatabase::class.java,
            "release.db")
            .addMigrations(migration_1_2)
            .build()
    }

    private val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE 'release' ADD COLUMN 'CATEGORYS' title")
        }
    }

    @Provides
    fun provideDao(database: ReleaseDatabase) : ReleaseDao {
        return database.baseDao()
    }
}