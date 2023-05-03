package com.roh.dogdom.data.messages

import android.util.Log

class MessagesRepositoryImpl ()
  : MessagesRepository {
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