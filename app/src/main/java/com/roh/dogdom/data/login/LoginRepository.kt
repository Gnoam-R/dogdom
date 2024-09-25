package com.roh.dogdom.data.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

interface LoginRepository {
    fun setLogin(activity : Activity, context: Context, resultLauncher: ActivityResultLauncher<Intent>)
    fun goLogin(type: LoginType) : Boolean
    fun goEmailSignUp() : Boolean
    fun goMasterLogin() : Boolean
    fun handleSignInResult(task: Task<GoogleSignInAccount>)
}