package com.roh.dogdom.views.release

import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentReleaseDynamicBinding
import com.roh.dogdom.navigator.AppNavigator
import com.roh.dogdom.util.MoveViewType
import com.roh.dogdom.util.VersionUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReleaseDynamicFragment : BaseFragment<FragmentReleaseDynamicBinding>(R.layout.fragment_release_dynamic){
    @Inject lateinit var navigator: AppNavigator
    private val viewModel by viewModels<ReleaseDynamicViewModel>()
    override fun init() {
        binding.vm = viewModel
        initViewModelCallback()
    }

    private val pickMultipleMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(3)) { uris ->
            // Callback is invoked after th user selects a media item or closes the photo picker.
            if (uris != null) {
                Log.d("PhotoPicker", "Selected URI: $uris")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    private fun initViewModelCallback() {
        with(viewModel) {
            btBack.observe(viewLifecycleOwner, Observer {
                moveView(MoveViewType.BACK)
            })
            btNext.observe(viewLifecycleOwner, Observer {
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
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivity(intent)
        }
    }
}