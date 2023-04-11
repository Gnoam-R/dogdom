package com.roh.dogdom.module

import android.app.Application
import com.roh.dogdom.data.TodoDatabase
import dagger.Module
import dagger.Provides
import androidx.room.Room
import com.roh.dogdom.data.TodoRepository
import com.roh.dogdom.data.TodoRepositoryImpl

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // @InstallIn : Hilt가 생성하는 DI 컨테이너에  어떤 모듈을 사용할지 가리킨다.
    @Provides
    @Singleton
    fun provideTOdoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.dao)
    }
}