package com.roh.dogdom.data.firebase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class FirebaseRepositoryImpl : FirebaseRepository {

//    lateinit var mContext: Context

    override fun init(context: Context) {
//        mContext = context
    }

    override fun downloadImage(image: ImageView, path: String) {
        // Reference to an image file in Cloud Storage
//        val imageView = binding.imageView

        val ref = FirebaseStorage.getInstance().getReference(path)
        ref.downloadUrl.addOnCompleteListener {task ->
            if (task.isSuccessful) {
                Glide.with(image.context)
                    .load(task.result)
                    .into(image)
            } else {
                Log.e("downLoadFb", "error")
            }
        }
    }
    override fun uploadImage(image: ImageView, path: String) {
//        val imageView = binding.imageView
        val mountainsRef = FirebaseStorage.getInstance().getReference().child("0604_file/aaa")
        // Get the data from an ImageView as bytes
        image.isDrawingCacheEnabled = true
        image.buildDrawingCache()
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...

        }
    }
}