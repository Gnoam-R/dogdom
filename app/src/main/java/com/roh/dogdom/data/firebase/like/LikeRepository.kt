package com.roh.dogdom.data.firebase.like

import android.content.Context
import android.widget.ImageView

interface LikeRepository {
    fun init()
    fun uploadToServer()
    fun downloadFromServer()
}