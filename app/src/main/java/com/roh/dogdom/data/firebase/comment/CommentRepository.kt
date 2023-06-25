package com.roh.dogdom.data.firebase.comment

import android.content.Context
import android.widget.ImageView

interface CommentRepository {
    fun init(context: Context)
    fun downloadImage(image: ImageView, path: String)
    fun uploadImage(image: ImageView, path: String)
}