package com.roh.dogdom.data.firebase

import android.content.Context
import android.widget.ImageView

interface FirebaseRepository {
    fun init(context: Context)
    fun downloadImage(image: ImageView, path: String)
    fun uploadImage(image: ImageView, path: String)
}