package com.roh.dogdom.data.login.google

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class GoogleLoginRepositoryImpl () : GoogleLoginRepository {

    private val TAG = "LoginRepositoryImpl"

    private lateinit var mActivity : Activity
    private lateinit var mContext : Context
    private lateinit var mView : View

    // 구글 소셜 로그인
    lateinit var mGoogleSignInClient: GoogleSignInClient

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestProfile()
        .requestId()
        .build()

    override fun setLogin(activity : Activity, context: Context) : Boolean {
        mActivity = activity
        mContext = context
        mGoogleSignInClient = GoogleSignIn.getClient(mActivity,gso)

//        setResultSignUP()
        return true
    }


    override fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val familyName = account?.familyName.toString()
            val givenName = account?.givenName.toString()
            val displayName = account?.displayName.toString()
            val photoUrl = account?.photoUrl.toString()
            val ID = account?.id.toString()

            //viewModel.UpdateData(displayName, ID, email, photoUrl,0)

            Log.e(TAG, "로그인한 유저의 이메일 ${email}")
            Log.e(TAG, "로그인한 유저의 성 ${familyName}")
            Log.e(TAG, "로그인한 유저의 이름 ${givenName}")
            Log.e(TAG, "로그인한 유저의 전체이름 ${displayName}")
            Log.e(TAG, "로그인한 유저의 프로필 사진의 주소 ${photoUrl}")
            Log.e(TAG, "로그인한 유저의 ID ${ID}")

        }catch (e: ApiException) {
            Log.e(TAG, "signInResult:failed code= ${e.statusCode}")
        }
    }

    override fun GoogleSignIn(resultLauncher: ActivityResultLauncher<Intent>) {
        val signIntent: Intent = mGoogleSignInClient.signInIntent
        Log.e(TAG, "signIntent ${signIntent}")
        resultLauncher.launch(signIntent)
    }
    override fun GoogleSignOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(mActivity) {

            }
    }
    override fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener(mActivity) {

            }
    }
    override fun GetCurrentUserProfile() {
        val curUser = com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(mContext)
        curUser?.let {
            val email = curUser.email.toString()
            val familyName = curUser.familyName.toString()
            val givenName = curUser.givenName.toString()
            val displayName = curUser.displayName.toString()
            val photoUrl = curUser.photoUrl.toString()

            Log.e(TAG, "현재 로그인한 유저의 이메일 ${email}")
            Log.e(TAG, "현재 로그인한 유저의 성 ${familyName}")
            Log.e(TAG, "현재 로그인한 유저의 이름 ${givenName}")
            Log.e(TAG, "현재 로그인한 유저의 전체이름 ${displayName}")
            Log.e(TAG, "현재 로그인한 유저의 프로필 사진의 주소 ${photoUrl}")
        }
    }

}