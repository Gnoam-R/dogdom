package com.roh.dogdom.views.home

import android.util.Log
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentHomeSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeSearchFragment :
    BaseFragment<FragmentHomeSearchBinding>(R.layout.fragment_home_search){

    override fun init() {
        with(binding) {
            btClose.setOnClickListener {
                Log.e("HomeSearchFragment", "btClose")
                var HomeFragment = HomeFragment()
                HomeFragment.binding.transformationLayout.finishTransform()
            }
        }
    }

    private fun moveNextScreen() {
//        findNavController().navigate(
//            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        )

    }
}