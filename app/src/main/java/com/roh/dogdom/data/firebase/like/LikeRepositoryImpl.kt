package com.roh.dogdom.data.firebase.like

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
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class LikeRepositoryImpl : LikeRepository {

    private var fireBaseRepository = FireBaseRepositoryImpl()

    private val InfoPah = "dogdom/like"
    var fbDatabase : FirebaseDatabase = fireBaseRepository.getFireBase()
    lateinit var fbDatabaseRef : DatabaseReference

    override fun init() {
        fbDatabase = fireBaseRepository.getFireBase()
        fbDatabaseRef = fbDatabase.getReference(InfoPah)
    }
    override fun uploadToServer() {
        fbDatabaseRef.setValue(LikeInfo())
    }
    override fun downloadFromServer() {
        fbDatabaseRef.get().addOnSuccessListener {
            Log.d("TAG", "downloadFromServer: ${it.value}")
        }
    }
}