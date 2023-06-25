package com.roh.dogdom.data.firebase.like

import android.content.Context
import android.widget.ImageView

interface LikeRepository {
    fun init(context: Context)
    fun downloadImage(image: ImageView, path: String)
    fun uploadImage(image: ImageView, path: String)
}