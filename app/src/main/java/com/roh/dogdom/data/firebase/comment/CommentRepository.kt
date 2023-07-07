package com.roh.dogdom.data.firebase.comment

import android.content.Context
import android.widget.ImageView

interface CommentRepository {
    fun init()
    fun uploadToServer(commentInfo: CommentInfo, path: String)
    fun downloadFromServer()
}