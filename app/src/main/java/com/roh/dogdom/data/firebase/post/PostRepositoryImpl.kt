package com.roh.dogdom.data.firebase.post

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.roh.dogdom.data.firebase.FireBaseRepository
import com.roh.dogdom.data.firebase.FireBaseRepositoryImpl
import com.roh.dogdom.data.firebase.user.UserInfo
import com.roh.dogdom.data.login.google.GoogleInfo
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class PostRepositoryImpl : PostRepository {

    private var fireBaseRepository = FireBaseRepositoryImpl()

    var fbDatabase : FirebaseDatabase = fireBaseRepository.getFireBase()
    lateinit var fbDatabaseRef : DatabaseReference

    override fun init() {
//        fbDatabaseRef = fbDatabase.getReference(InfoPah)
    }
    override fun uploadToServer(userInfo : UserInfo, path: String) {
        fbDatabaseRef = fbDatabase.getReference(path)
        fbDatabaseRef.setValue(userInfo)
    }

    override fun downloadFromServer(path: String) {
        fbDatabaseRef = fbDatabase.getReference(path)
        fbDatabaseRef.get().addOnSuccessListener {
            Log.d("TAG", "downloadFromServer: ${it.value}")
        }
    }
    override fun downloadImage(image: ImageView, path: String) {
        // 이미지뷰 크기에 맞춰 이미지 크기 조정
        val ref = FirebaseStorage.getInstance().getReference(path)
//        Log.e("downLoadFb", "Image : $path")

        ref.downloadUrl.addOnCompleteListener {task ->
            if (task.isSuccessful) {
//                Log.e("downLoadFb", "Image : ${image.width}, ${image.height}")
                Glide.with(image.context)
                    .load(task.result)
                    .apply(RequestOptions()
                        .override(339,200)
                        .centerCrop())
                    .into(image)
            } else {
                Log.e("downLoadFb", "error")
            }
        }
    }
    override fun uploadImage(image: ImageView, path: String) {
//        val imageView = binding.imageView
        val mountainsRef = FirebaseStorage.getInstance().getReference().child("file")
        // Get the data from an ImageView as bytes
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {

        }.addOnSuccessListener { taskSnapshot ->

        }
    }


}