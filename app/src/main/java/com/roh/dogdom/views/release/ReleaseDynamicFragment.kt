package com.roh.dogdom.views.release

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.db.release.local.ReleaseEntity
import com.roh.dogdom.data.db.release.local.ReleaseLocalDataSource
import com.roh.dogdom.databinding.FragmentReleaseDynamicBinding
import com.roh.dogdom.navigator.AppNavigator
import com.roh.dogdom.util.MoveViewType
import com.roh.dogdom.util.VersionUtils
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class ReleaseDynamicFragment : BaseFragment<FragmentReleaseDynamicBinding>(R.layout.fragment_release_dynamic){
    @Inject lateinit var navigator: AppNavigator
    @Inject lateinit var releaseDb: ReleaseLocalDataSource

    private val viewModel by viewModels<ReleaseDynamicViewModel>()
    private var releaseEntity = ReleaseEntity(
        userId = 1004
    )

    private val pickMultipleMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(3)) { uris ->
            // Callback is invoked after th user selects a media item or closes the photo picker.
            if (uris != null) {
                Log.d("PhotoPicker", "Selected URI: $uris")
                releaseEntity.imageUri = uris.toString()
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    private var getImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { uri ->
                releaseEntity.imageUri = uri.toString()
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun init() {
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun insertRelease(info: ReleaseEntity) {
        releaseDb.add(info)
        releaseDb.getAll {
            Log.e("releaseDb ", "${it}")
        }
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            btBack.observe(viewLifecycleOwner, Observer {
                moveView(MoveViewType.BACK)
            })
            btNext.observe(viewLifecycleOwner, Observer {
                releaseEntity.title = releaseTitle.value
                releaseEntity.comment = releaseComment.value
                insertRelease(releaseEntity)
                moveView(MoveViewType.NEXT)
            })
            loadImage.observe(viewLifecycleOwner, Observer {
                pickImageFromGallery()
            })
        }
    }
    private fun moveView(type: MoveViewType) {
        val direction: NavDirections = when (type) {
            MoveViewType.BACK -> ReleaseDynamicFragmentDirections.actionReleaseDynamicFragmentToHomeFragment()
            MoveViewType.NEXT -> ReleaseDynamicFragmentDirections.actionReleaseDynamicFragmentToHomeFragment()
            else -> throw IllegalArgumentException("Invalid MoveViewType")
        }
        findNavController().navigate(direction)
    }

    private fun pickImageFromGallery() {
        if(VersionUtils.hasTiramisu()) {
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
        else {
            // 이미지 선택 Intent 실행
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            getImageLauncher.launch(intent)
        }
    }

    private fun resetView() {
        binding.ctImage.visibility = View.VISIBLE
        binding.ctSecond.visibility = View.GONE
    }

    private fun isImageLoaded() {
        val isImageLoaded =  pref.getString("isImageLoaded", null)
        Log.e("ReleaseDynamicFragment-check", "onStart: isImageLoaded $isImageLoaded")
        if(isImageLoaded != "null") {
            binding.ctImage.visibility = View.GONE
            binding.ctSecond.visibility = View.VISIBLE
        }
        else {
            Log.e("ReleaseDynamicFragment", "$isImageLoaded")
        }
    }

    private fun loadImage(imageUri: Uri) {
        try {
            Log.e("ReleaseDynamicFragment", "imageUri: $imageUri")
            val bitmap = MediaStore.Images.Media.getBitmap(mContext.contentResolver, imageUri)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}