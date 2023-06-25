package com.roh.dogdom.data.firebase

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface FireBaseRepository {
    fun getFireBase() : FirebaseDatabase
    fun init()
}