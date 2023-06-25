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
import com.roh.dogdom.data.firebase.user.UserInfo
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(var fireBaseRepository : FireBaseRepository): LikeRepository {

    val UserInfoPah = "dogdom/like"
    lateinit var fbDatabase : FirebaseDatabase
    lateinit var fbDatabaseRef : DatabaseReference

    override fun init() {
        fbDatabase = fireBaseRepository.getFireBase()
        fbDatabaseRef = fbDatabase.getReference(UserInfoPah)
    }
    override fun uploadToServer() {
        fbDatabaseRef.setValue(UserInfo())
    }
    override fun downloadFromServer() {
        fbDatabaseRef.get().addOnSuccessListener {
            Log.d("TAG", "downloadFromServer: ${it.value}")
        }
    }
}