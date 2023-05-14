package com.roh.dogdom.views.release

import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.permission.PermissionRepository
import com.roh.dogdom.data.permission.PermissionRepositoryImpl
import com.roh.dogdom.databinding.FragmentReleaseArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleaseArticleFragment : BaseFragment<FragmentReleaseArticleBinding>(R.layout.fragment_release_article){
//    private val viewModel: LocalPageViewModel by viewModel()
//    private val viewModel by viewModels<LoginViewModel>()

    private val repository: PermissionRepository = PermissionRepositoryImpl()

    override fun init() {

    }
}