package com.roh.dogdom.views.home

import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentHomeFirstContentsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeContentsFirstFragment :
    BaseFragment<FragmentHomeFirstContentsBinding>(R.layout.fragment_home_first_contents) {

    override fun init() {

    }

    private fun moveNextScreen() {
//        findNavController().navigate(
//            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        )
    }
}