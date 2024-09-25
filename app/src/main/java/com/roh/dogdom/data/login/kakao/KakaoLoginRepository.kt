package com.roh.dogdom.data.login.kakao

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.roh.dogdom.data.login.google.GoogleInfo

interface KakaoLoginRepository {
    fun setLogin(activity: Activity, context: Context, ) : Boolean
    fun signIn()
    fun signOut()
    fun revokeAccess()
    fun GetCurrentUserProfile(upload: (GoogleInfo) -> Unit)
}