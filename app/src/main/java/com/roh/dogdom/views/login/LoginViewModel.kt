package com.roh.dogdom.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.roh.dogdom.data.bottommenu.BottomMenuRepository
import com.roh.dogdom.data.login.LoginRepository
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val bottomMenuRepository: BottomMenuRepository
) : ViewModel(){

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
        loginRepository.googleLogin()
    }
    fun goKakaoLogin() {
        loginRepository.kakaoLogin()
    }
    fun setBottomNav() {
        Log.e("LoginViewModel", "setBottomNav")
        bottomMenuRepository.setBottomNavigation()
    }


}