package com.roh.dogdom.views.splash

import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private var job = CoroutineScope.launch {
        // 비동기 작업 처리
    }


    override fun init() {
        CoroutineScope.launch {

        }
    }
}