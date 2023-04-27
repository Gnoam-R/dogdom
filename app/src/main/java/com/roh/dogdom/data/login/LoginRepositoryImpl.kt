package com.roh.dogdom.data.login

import android.util.Log

class LoginRepositoryImpl ()
  : LoginRepository {
    override fun kakaoLogin(): Boolean {
        return true
    }

    override fun goEmailSignUp(): Boolean {
        Log.e("LoginRepositoryImpl", "goEmailSignUp")
        return true
    }

    override fun goMasterLogin(): Boolean {
        return true
    }

    override fun googleLogin(): Boolean {
        return true
    }
}