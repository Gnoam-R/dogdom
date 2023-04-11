package com.roh.dogdom.views.splash

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentSplashBinding
import kotlinx.coroutines.*

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private lateinit var job : Job

    override fun init() {
        showSplashImage()
    }

    private fun showSplashImage() {
        job = CoroutineScope(Dispatchers.IO).launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 비동기 작업 처리
                delay(1000)
                // 메인 스레드에서 실행
                withContext(Dispatchers.Main) {
                    moveNextScreen()
                }
                // 비동기 작업 종료
                job.cancel()
            }
        }
    }

    private fun moveNextScreen() {
        if(isAdded) {
            findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            )
        }
    }

}