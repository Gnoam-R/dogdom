package com.roh.dogdom.views.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.roh.dogdom.data.base.BaseViewModel
import com.roh.dogdom.data.login.LoginRepository
import com.roh.dogdom.data.login.LoginRepositoryImpl
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton


@HiltViewModel
class LoginViewModel @Inject constructor(
    @Singleton
    private val loginRepository: LoginRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


//    fun goEmailSignUp() : Boolean {
////        loginRepository.goEmailSignUp()
//        return true
//    }
//
//    fun goMasterLogin() : Boolean {
////        loginRepository.goMasterLogin()
//        return true
//    }
//
//    fun onBackButtonClick() {
////        _backClick.call()
//    }
}