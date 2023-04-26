package com.roh.dogdom.views.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.data.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel(){

    init {
        Log.e("LoginViewModel", "${repository.goEmailSignUp()}")
    }

    fun Log() {
        Log.e("LoginViewModel", "check")
    }

    fun moveNextScreen() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToMasterMainFragment()
        )
    }
}