package com.roh.dogdom.data.firebase.user

import android.content.Context
import android.widget.ImageView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

interface UserRepository {
    fun init()
    fun uploadToServer()
    fun downloadFromServer()
}