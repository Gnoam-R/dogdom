package com.roh.dogdom.views.release

import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.permission.PermissionRepository
import com.roh.dogdom.data.permission.PermissionRepositoryImpl
import com.roh.dogdom.databinding.FragmentReleaseBinding
import com.roh.dogdom.databinding.FragmentReleaseDynamicBinding
import com.roh.dogdom.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleaseDynamicFragment : BaseFragment<FragmentReleaseDynamicBinding>(R.layout.fragment_release_dynamic){
//    private val viewModel: LocalPageViewModel by viewModel()
//    private val viewModel by viewModels<LoginViewModel>()

    private val repository: PermissionRepository = PermissionRepositoryImpl()

    override fun init() {

    }
}