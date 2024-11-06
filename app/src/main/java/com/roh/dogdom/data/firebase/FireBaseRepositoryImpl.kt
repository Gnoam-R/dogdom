package com.roh.dogdom.data.firebase

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FireBaseRepositoryImpl : FireBaseRepository {

    lateinit var fbDatabase : FirebaseDatabase
    override fun getFireBase() : FirebaseDatabase {
        fbDatabase = Firebase.database
        return fbDatabase
    }

    override fun init() {

    }
}