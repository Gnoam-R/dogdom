package com.roh.dogdom.views.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.roh.dogdom.data.bottommenu.BottomMenuRepository
import com.roh.dogdom.data.login.LoginRepository
import com.roh.dogdom.data.login.google.GoogleLoginRepository
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val bottomMenuRepository: BottomMenuRepository,
    private val googleLoginRepository: GoogleLoginRepository
) : ViewModel(){

    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var mActivity: Activity

    private val _goMain = SingleLiveEvent<Unit>()
    val goMain: LiveData<Unit> get() = _goMain

    private val _goEmailSignUp = SingleLiveEvent<Unit>()
    val goEmailSignUp: LiveData<Unit> get() = _goEmailSignUp

//    private val _setBtMenu = SingleLiveEvent<Unit>()
//    val setBtMenu: LiveData<Unit> get() = _setBtMenu

    fun goMain() {
        _goMain.call()
    }
    fun goGoogleLogin() {
//        loginRepository.googleLogin()
        googleLoginRepository.GoogleSignIn(resultLauncher)
    }
    fun goKakaoLogin() {
        loginRepository.kakaoLogin()
    }
    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        googleLoginRepository.handleSignInResult(task)
    }
    fun setBottomNav() {
        Log.e("LoginViewModel", "setBottomNav")
        bottomMenuRepository.setBottomNavigation()
    }
    fun setLogin(activity : Activity, context: Context, resultLauncher: ActivityResultLauncher<Intent>) {
        this.resultLauncher = resultLauncher
        googleLoginRepository.setLogin(activity, context)
    }
}