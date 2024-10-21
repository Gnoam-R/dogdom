package com.roh.dogdom.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.roh.dogdom.data.db.user.UserLocalDataSource
import com.roh.dogdom.data.firebase.comment.CommentRepository
import com.roh.dogdom.data.firebase.like.LikeRepository
import com.roh.dogdom.data.firebase.post.PostRepository
import com.roh.dogdom.data.firebase.user.UserRepository
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    @Inject lateinit var userDB: UserLocalDataSource

    @Inject lateinit var commentRepository: CommentRepository
    @Inject lateinit var likeRepository: LikeRepository
    @Inject lateinit var postRepository: PostRepository
    @Inject lateinit var userRepository: UserRepository

    lateinit var fbStorage : FirebaseStorage
    private val _goEx = SingleLiveEvent<Unit>()
    val goEx: LiveData<Unit> get() = _goEx

    fun goEx() {
        _goEx.call()
    }
    private fun downLoadFB() {
        val storageRef = fbStorage.reference
        val pathReference = storageRef.child("0604_file/0604_image1")
        val gsReference = fbStorage.getReferenceFromUrl("gs://bucket/images/stars.jpg")
        val httpsReference = fbStorage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg",
        )
    }
}