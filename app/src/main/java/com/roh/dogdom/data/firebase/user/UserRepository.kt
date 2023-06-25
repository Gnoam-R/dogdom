package com.roh.dogdom.data.firebase.user

import android.content.Context
import android.widget.ImageView

interface UserRepository {
    fun init(context: Context)
    fun downloadImage(image: ImageView, path: String)
    fun uploadImage(image: ImageView, path: String)
}