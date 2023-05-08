package com.roh.dogdom.views.home

import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentMasterMainFirstContentsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeContentsFirstFragment :
    BaseFragment<FragmentMasterMainFirstContentsBinding>(R.layout.fragment_master_main_first_contents) {

    override fun init() {

    }

    private fun moveNextScreen() {
//        findNavController().navigate(
//            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        )
    }
}