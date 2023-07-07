package com.roh.dogdom.data.firebase.comment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.roh.dogdom.data.firebase.FireBaseRepository
import com.roh.dogdom.data.firebase.FireBaseRepositoryImpl
import com.roh.dogdom.data.firebase.user.UserInfo


class CommentRepositoryImpl : CommentRepository {

    private var fireBaseRepository = FireBaseRepositoryImpl()

    private val InfoPah = "dogdom/comment"
    var fbDatabase : FirebaseDatabase = fireBaseRepository.getFireBase()
    lateinit var fbDatabaseRef : DatabaseReference

    override fun init() {
//        Log.e("sdfd", "test")
//        fbDatabase = fireBaseRepository.getFireBase()
//        fbDatabaseRef = fbDatabase.getReference(InfoPah)
    }
    override fun uploadToServer(commentInfo: CommentInfo, path: String) {
        fbDatabaseRef = fbDatabase.getReference(path)
        fbDatabaseRef.setValue(commentInfo)
    }
    override fun downloadFromServer() {
        fbDatabaseRef.get().addOnSuccessListener {
            Log.d("TAG", "downloadFromServer: ${it.value}")
        }
    }
}