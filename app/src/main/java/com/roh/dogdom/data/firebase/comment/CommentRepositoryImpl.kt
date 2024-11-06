package com.roh.dogdom.data.firebase.comment

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.roh.dogdom.data.firebase.FireBaseRepositoryImpl

class CommentRepositoryImpl : CommentRepository {

    private var fireBaseRepository = FireBaseRepositoryImpl()

    private val InfoPah = "dogdom/comment"
    var fbDatabase : FirebaseDatabase = fireBaseRepository.getFireBase()
    lateinit var fbDatabaseRef : DatabaseReference

    override fun init() {
        fbDatabase = fireBaseRepository.getFireBase()
        fbDatabaseRef = fbDatabase.getReference(InfoPah)
    }
    override fun uploadToServer(commentInfo: CommentInfo, path: String) {
        fbDatabaseRef = fbDatabase.getReference(path)
        fbDatabaseRef.setValue(commentInfo)
    }
    override fun downloadFromServer() {
        fbDatabaseRef.get().addOnSuccessListener {
            Log.d("TAG", "downloadFromServer: ${it.value}")
        }
    }
}