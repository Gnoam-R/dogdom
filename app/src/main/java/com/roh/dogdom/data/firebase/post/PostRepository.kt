package com.roh.dogdom.data.firebase.post

import android.content.Context
import android.widget.ImageView
import com.roh.dogdom.data.firebase.user.UserInfo

interface PostRepository {
    fun init()
    fun downloadImage(image: ImageView, path: String)
    fun uploadImage(image: ImageView, path: String)
    fun uploadToServer(userInfo : UserInfo, path: String)
    fun downloadFromServer(path: String)
}