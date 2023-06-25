package com.roh.dogdom.data.firebase


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.roh.dogdom.data.firebase.comment.CommentRepository
import com.roh.dogdom.data.firebase.like.LikeRepository
import com.roh.dogdom.data.firebase.post.PostRepository
import com.roh.dogdom.data.firebase.user.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class FireBaseRepositoryImpl : FireBaseRepository {

//    @Inject lateinit var commentRepository: CommentRepository
//    @Inject lateinit var likeRepository: LikeRepository
//    @Inject lateinit var postRepository: PostRepository

    lateinit var fbDatabase : FirebaseDatabase
    override fun getFireBase() : FirebaseDatabase {
        fbDatabase = Firebase.database
        return fbDatabase
    }

    override fun init() {
//        userRepository.init()
//        commentRepository.init()
//        likeRepository.init()
//        postRepository.init()
    }

}