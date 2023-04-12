package com.roh.dogdom.views.login

import com.roh.dogdom.base.BaseViewModel
import com.roh.dogdom.data.login.LoginRepository



class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel (){

//    fun kakaoLogin() : Boolean {
//
//
//        viewModelScope.launch {
//            loginRepository.kakaoLogin()
//            return true
//        }
//
//    }

    fun goEmailSignUp() : Boolean {
        loginRepository.goEmailSignUp()
        return true
    }

    fun goMasterLogin() : Boolean {
        loginRepository.goMasterLogin()
        return true
    }

    fun onBackButtonClick() {
//        _backClick.call()
    }
}