package com.roh.dogdom.views.release

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.permission.PermissionRepository
import com.roh.dogdom.data.permission.PermissionRepositoryImpl
import com.roh.dogdom.databinding.FragmentReleaseBinding
import com.roh.dogdom.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import com.bumptech.glide.Glide
import com.roh.dogdom.data.dogimage.DogImageRepository
import com.roh.dogdom.data.dogimage.DogImageRepositoryImpl
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class ReleaseFragment : BaseFragment<FragmentReleaseBinding>(R.layout.fragment_release){
//    private val viewModel: LocalPageViewModel by viewModel()
//    private val viewModel by viewModels<LoginViewModel>()

    private val repository: PermissionRepository = PermissionRepositoryImpl()
    private val dogImageRepository: DogImageRepository = DogImageRepositoryImpl()

    private fun downLoadFB2() {
        // Reference to an image file in Cloud Storage
        val imageView = binding.imageView

//        dogImageRepository.initDogApi()
//        dogImageRepository.requestDogAPi()

        val ref = FirebaseStorage.getInstance().getReference("0604_file/0604_image3.jpeg")
        ref.downloadUrl.addOnCompleteListener {task ->
            if (task.isSuccessful) {
                Glide.with(this)
                    .load(task.result)
                    .into(imageView)
            } else {
                Log.e("downLoadFb", "error")
            }
        }
    }
    private fun upLoadFB() {
        val imageView = binding.imageView
        val mountainsRef = FirebaseStorage.getInstance().getReference().child("0604_file/aaa")
        // Get the data from an ImageView as bytes
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
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

    override fun init() {
        downLoadFB2()
        upLoadFB()
        binding.apply {
            btReleaseArticle.setOnClickListener {
                findNavController().navigate(R.id.action_releaseFragment_to_releaseArticleFragment)
            }
            btReleaseDynamic.setOnClickListener {
                findNavController().navigate(R.id.action_releaseFragment_to_releaseDynamicFragment)
            }

        }
    }
}