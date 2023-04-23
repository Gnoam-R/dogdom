package com.roh.dogdom.data.login

class LoginRepositoryImpl ()
  : LoginRepository {
    override fun kakaoLogin(): Boolean {
        return true
    }

    override fun goEmailSignUp(): Boolean {
        return true
    }

    override fun goMasterLogin(): Boolean {
        return true
    }

    override fun googleLogin(): Boolean {
        return true
    }
}