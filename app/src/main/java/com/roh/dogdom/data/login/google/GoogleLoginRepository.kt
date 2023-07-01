package com.roh.dogdom.data.login.google

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.roh.dogdom.data.firebase.user.UserInfo

interface GoogleLoginRepository {

    fun setLogin(activity : Activity, context: Context, ) : Boolean     // kakao
    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>)      // kakao
    fun GoogleSignIn(resultLauncher: ActivityResultLauncher<Intent>)
    fun GoogleSignOut()
    fun revokeAccess()
    fun GetCurrentUserProfile(upload: (GoogleInfo) -> Unit)

}