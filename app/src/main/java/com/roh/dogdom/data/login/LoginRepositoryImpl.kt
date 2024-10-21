package com.roh.dogdom.data.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.roh.dogdom.data.db.user.UserEntity
import com.roh.dogdom.data.db.user.UserLocalDataSource
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
        kakaoLoginRepository.GetCurrentUserProfile { userInfo ->

        }
        return true
    }
    private fun googleLogin(): Boolean {
        googleLoginRepository.signIn()
        googleLoginRepository.GetCurrentUserProfile{ userInfo ->
            if (isGetCurrentUserInfo()) {

            }
            else {
//                userDB.addUser(UserEntity(
//                    name = userInfo.name,
//                    profile_image = userInfo.profileAddress,
//                    account_id = userInfo.userId,
//                ))

                userRepository.uploadToServer(
                    userInfo,
                    path = "dogdom/user/user-google-${userInfo.userId}"
                )

//                userDB.getAllUsers {
//                    Log.e("LoginRepositoryImpl", "$it")
//                }
            }
        }
        return true
    }

    private fun isGetCurrentUserInfo(): Boolean {
        return false
    }
}