package com.roh.dogdom.data.login.google

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

interface GoogleLoginRepository {
    fun setLogin(activity : Activity, context: Context, resultLauncher: ActivityResultLauncher<Intent>) : Boolean     // kakao
    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>)      // kakao
    fun signIn()
    fun signOut()
    fun revokeAccess()
    fun GetCurrentUserProfile(upload: (GoogleInfo) -> Unit)
}