package com.roh.dogdom.views.home

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.home.HomeItem
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.data.home.MainPost2
import com.roh.dogdom.databinding.FragmentHomeSearchBinding
import com.roh.dogdom.databinding.FragmentHomeSecondContentsBinding
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