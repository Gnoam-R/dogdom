package com.roh.dogdom.data.firebase.user

import android.content.Context
import android.widget.ImageView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.roh.dogdom.data.login.google.GoogleInfo

interface UserRepository {
    fun init()
    fun uploadToServer(userInfo : UserInfo, path: String)
    fun uploadToServer(userInfo : GoogleInfo, path: String)
    fun downloadFromServer()
}