package com.roh.dogdom.views.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.roh.dogdom.data.bottommenu.BottomNavigationRepository
import com.roh.dogdom.data.login.LoginRepository
import com.roh.dogdom.data.login.LoginType
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val BottomNavigationRepository: BottomNavigationRepository,
) : ViewModel(){

    lateinit var mActivity: Activity
    private val _goMain = SingleLiveEvent<Unit>()
    val goMain: LiveData<Unit> get() = _goMain
    fun goMain() {
        _goMain.call()
    }

    fun goLogin(num: Int) {
        when (num) {
            1 -> loginRepository.goLogin(LoginType.GOOGLE)
            2 -> loginRepository.goLogin(LoginType.KAKAO)
        }
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {

    }
    fun setBottomNav() {
        BottomNavigationRepository.setBottomNavigation()
    }
    fun setLogin(activity : Activity, context: Context, resultLauncher: ActivityResultLauncher<Intent>) {
        loginRepository.setLogin(activity, context, resultLauncher)
    }
}