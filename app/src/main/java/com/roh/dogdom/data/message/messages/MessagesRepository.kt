package com.roh.dogdom.data.message.messages

interface MessagesRepository {
    fun kakaoLogin() : Boolean
    fun goEmailSignUp() : Boolean
    fun goMasterLogin() : Boolean
    fun googleLogin() : Boolean
}