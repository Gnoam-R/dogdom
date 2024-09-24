package com.roh.dogdom.data.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.roh.dogdom.data.login.google.GoogleLoginRepository
import com.roh.dogdom.data.login.kakao.KakaoLoginRepository
import javax.inject.Inject
import com.roh.dogdom.data.firebase.user.UserRepository

class LoginRepositoryImpl @Inject constructor(
    private val googleLoginRepository: GoogleLoginRepository,
    private val kakaoLoginRepository: KakaoLoginRepository,
    private val userRepository: UserRepository,
)
  : LoginRepository {
    private val TAG = this::class.simpleName

    override fun goLogin(type: LoginType): Boolean {
        when (type) {
            LoginType.KAKAO -> kakaoLogin()
            LoginType.GOOGLE -> googleLogin()
            else -> return false
        }
        return true
    }
    override fun goEmailSignUp(): Boolean {
        return true
    }

    override fun goMasterLogin(): Boolean {
        return true
    }

    override fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        googleLoginRepository.handleSignInResult(task)
    }

    override fun setLogin(activity : Activity, context: Context, resultLauncher: ActivityResultLauncher<Intent>) {
        googleLoginRepository.setLogin(activity, context, resultLauncher)
        kakaoLoginRepository.setLogin(activity, context)
    }
    private fun kakaoLogin(): Boolean {
        kakaoLoginRepository.signIn()
        return true
    }
    private fun googleLogin(): Boolean {
        googleLoginRepository.signIn()
        googleLoginRepository.GetCurrentUserProfile{ userInfo ->
            val InfoPath = "dogdom/user/user-google-${userInfo.userId}"
            userRepository.uploadToServer(userInfo, InfoPath)
        }
        return true
    }
}