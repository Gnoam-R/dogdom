package com.roh.dogdom.views.login

import android.app.Activity
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
import com.roh.dogdom.data.db.user.UserLocalDataSource
import com.roh.dogdom.databinding.FragmentLoginBinding
import com.roh.dogdom.util.enumUiColorPos
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login){
    private val viewModel by viewModels<LoginViewModel>()
    @Inject lateinit var userDB: UserLocalDataSource
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