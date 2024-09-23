package com.roh.dogdom.data.login.kakao

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.roh.dogdom.R
import com.roh.dogdom.data.login.google.GoogleInfo

class KakaoLoginRepositoryImpl: KakaoLoginRepository {
    private val TAG = this::class.simpleName
    private lateinit var mActivity : Activity
    private lateinit var mContext : Context

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
    }
    override fun setLogin(activity: Activity, context: Context): Boolean {
        Log.e(TAG, "setLogin")
        mActivity = activity
        mContext = context
        val kakaoSdkAppkeyString = mContext.getString(R.string.kakao_app_key)
        KakaoSdk.init(mContext, kakaoSdkAppkeyString)
        return true
    }

    override fun signIn() {
        Log.e(TAG, "signIn")
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)) {
            Log.e(TAG, "카카오계정으로 로그인 실패----3")
            UserApiClient.instance.loginWithKakaoTalk(mContext) { token, error ->
                // 로그인 실패 부분
                if (error != null) {
                    Log.e(TAG, "로그인 실패 $error")
                    // 사용자가 취소
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled ) {
                        return@loginWithKakaoTalk
                    }
                    // 다른 오류
                    else {
                        UserApiClient.instance.loginWithKakaoAccount(mContext, callback = callback) // 카카오 이메일 로그인
                    }
                }
                // 로그인 성공 부분
                else if (token != null) {
                    Log.e(TAG, "로그인 성공 ${token.accessToken}")
                }
            }
        }
        else {
            UserApiClient.instance.loginWithKakaoAccount(mContext, callback = callback) // 카카오 이메일 로그인
        }
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun revokeAccess() {
        TODO("Not yet implemented")
    }

    override fun GetCurrentUserProfile(upload: (GoogleInfo) -> Unit) {
        TODO("Not yet implemented")
    }
}