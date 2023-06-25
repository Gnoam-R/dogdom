package com.roh.dogdom.data.firebase.user

import android.content.Context
import android.util.Log
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.roh.dogdom.data.firebase.FireBaseRepository
import com.roh.dogdom.data.firebase.FireBaseRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class UserRepositoryImpl : UserRepository {

    private var fireBaseRepository = FireBaseRepositoryImpl()

    private val InfoPah = "dogdom/user"
    lateinit var fbDatabase : FirebaseDatabase
    lateinit var fbDatabaseRef : DatabaseReference

    override fun init() {
        fbDatabase = fireBaseRepository.getFireBase()
        fbDatabaseRef = fbDatabase.getReference(InfoPah)
    }
    override fun uploadToServer() {
        fbDatabaseRef.setValue(UserInfo())
    }
    override fun downloadFromServer() {
        fbDatabaseRef.get().addOnSuccessListener {
            Log.d("TAG", "downloadFromServer: ${it.value}")
        }
    }
}