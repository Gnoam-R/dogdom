package com.roh.dogdom.views.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.roh.dogdom.data.base.BaseViewModel
import com.roh.dogdom.data.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


//@HiltViewModel
//class LoginViewModel @Inject constructor(
//    private val loginRepository: LoginRepository,
//    savedStateHandle: SavedStateHandle
//) : ViewModel() {
//
//
//
//    fun goEmailSignUp() : Boolean {
//        loginRepository.goEmailSignUp()
//        return true
//    }
//
//    fun goMasterLogin() : Boolean {
//        loginRepository.goMasterLogin()
//        return true
//    }
//
//    fun onBackButtonClick() {
////        _backClick.call()
//    }
//}