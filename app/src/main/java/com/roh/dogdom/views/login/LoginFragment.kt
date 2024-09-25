package com.roh.dogdom.views.login

import android.Manifest
import android.app.Activity
import android.app.NotificationManager
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.roh.dogdom.views.startup.MainActivity
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.login.kakao.KakaoLoginRepository
import com.roh.dogdom.data.login.kakao.KakaoLoginRepositoryImpl
import com.roh.dogdom.data.permission.PermissionRepository
import com.roh.dogdom.data.permission.PermissionRepositoryImpl
import com.roh.dogdom.databinding.FragmentLoginBinding
import com.roh.dogdom.util.enumUiColorPos
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login){
    private val viewModel by viewModels<LoginViewModel>()

    override fun init() {
        mActivity = activity as MainActivity
        setSystemStatusBarLayout()
        SystemUiChangeColor(enumUiColorPos.totalUiBarWhite)

        binding.vm = viewModel

        // google 소셜 로그인
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // 정상적으로 결과가 받아들여지면 조건문 실행
            if (result.resultCode == Activity.RESULT_OK){
                val task: Task<GoogleSignInAccount> =
                    com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(result.data)
                viewModel.handleSignInResult(task)
                viewModel.goMain()
            }
        }
        viewModel.setLogin(mActivity,mContext,resultLauncher)
        initViewModelCallback()
    }
    private fun initViewModelCallback() {
        with(viewModel) {
            goMain.observe(viewLifecycleOwner, Observer {
                // 바텀 네비게이션 설정
                setBottomNav()
                moveNextView()
            })
        }
    }

    private fun moveNextView() {
        val direction: NavDirections = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(direction)
    }
}