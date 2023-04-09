package com.roh.dogdom.data.login

interface LoginRepository {
    fun kakaoLogin() : Boolean
    fun goEmailSignUp() : Boolean
    fun goMasterLogin() : Boolean
}