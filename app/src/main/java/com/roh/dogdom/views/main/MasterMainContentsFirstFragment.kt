package com.roh.dogdom.views.main

import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentMasterMainFirstContentsBinding
import com.roh.dogdom.views.splash.SplashFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MasterMainContentsFirstFragment :
    BaseFragment<FragmentMasterMainFirstContentsBinding>(R.layout.fragment_master_main_first_contents) {

    override fun init() {

    }

    private fun moveNextScreen() {
//        findNavController().navigate(
//            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        )
    }
}