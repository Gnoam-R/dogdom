package com.roh.dogdom.data.firebase.post

import android.content.Context
import android.widget.ImageView

interface PostRepository {
    fun init()
    fun downloadImage(image: ImageView, path: String)
    fun uploadImage(image: ImageView, path: String)
}