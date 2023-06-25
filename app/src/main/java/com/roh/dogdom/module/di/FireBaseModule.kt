package com.roh.dogdom.module.di

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
import com.roh.dogdom.data.firebase.FireBaseRepository
import com.roh.dogdom.data.firebase.FireBaseRepositoryImpl
import com.roh.dogdom.data.firebase.comment.CommentRepository
import com.roh.dogdom.data.firebase.comment.CommentRepositoryImpl
import com.roh.dogdom.data.firebase.like.LikeRepository
import com.roh.dogdom.data.firebase.like.LikeRepositoryImpl
import com.roh.dogdom.data.firebase.post.PostRepository
import com.roh.dogdom.data.firebase.post.PostRepositoryImpl
import com.roh.dogdom.data.firebase.user.UserRepositoryImpl
import com.roh.dogdom.data.firebase.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FireBaseModule {
    @Singleton
    @Provides
    fun initializeFireBase(
    ) : FireBaseRepository {
        return FireBaseRepositoryImpl()
    }
    @Singleton
    @Provides
    fun provideUserRepository(
    ) : UserRepository {
        return UserRepositoryImpl (FireBaseRepositoryImpl())
    }
    @Singleton
    @Provides
    fun provideCommentRepository(
    ) : CommentRepository {
        return CommentRepositoryImpl (FireBaseRepositoryImpl())
    }
    @Singleton
    @Provides
    fun provideLikeRepository(
    ) : LikeRepository {
        return LikeRepositoryImpl (FireBaseRepositoryImpl())
    }
    @Singleton
    @Provides
    fun providePostRepository(
    ) : PostRepository {
        return PostRepositoryImpl (FireBaseRepositoryImpl())
    }
}
