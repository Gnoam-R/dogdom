package com.roh.dogdom.views.release

import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.permission.PermissionRepository
import com.roh.dogdom.data.permission.PermissionRepositoryImpl
import com.roh.dogdom.databinding.FragmentReleaseBinding
import com.roh.dogdom.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleaseFragment : BaseFragment<FragmentReleaseBinding>(R.layout.fragment_release){
//    private val viewModel: LocalPageViewModel by viewModel()
//    private val viewModel by viewModels<LoginViewModel>()

    private val repository: PermissionRepository = PermissionRepositoryImpl()

    override fun init() {
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