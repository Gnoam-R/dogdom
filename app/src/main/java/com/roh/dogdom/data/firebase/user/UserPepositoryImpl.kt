package com.roh.dogdom.data.firebase.user

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class UserPepositoryImpl : UserRepository {

//    lateinit var mContext: Context
    override fun init(context: Context) {
//        mContext = context
    }
//    val bitmap: Bitmap = BitmapFactory.decodeFile(absolutePath)
    override fun downloadImage(image: ImageView, path: String) {
        // Reference to an image file in Cloud Storage
//        val imageView = binding.imageView

        // 이미지뷰 크기에 맞춰 이미지 크기 조정
        val ref = FirebaseStorage.getInstance().getReference(path)
        ref.downloadUrl.addOnCompleteListener {task ->
            if (task.isSuccessful) {
                Log.e("downLoadFb", "Image : ${image.width}, ${image.height}")
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