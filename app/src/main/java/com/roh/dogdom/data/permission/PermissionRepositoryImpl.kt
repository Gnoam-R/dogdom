package com.roh.dogdom.data.permission

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

class PermissionRepositoryImpl () : PermissionRepository {

    private val TAG = "PermissionRepositoryImpl"

    private lateinit var mActivity : Activity
    private lateinit var mPermissions: Array<String>
    private var mPermissionRequset : Int = 0

    val Activity.rootView: View get() = findViewById<ViewGroup>(android.R.id.content).getChildAt(0)

    private val snackBarContainer: View
        get() = mActivity.rootView

    override fun setPermissions(activity : Activity, permissions: Array<String>, PERMISSION_REQUEST: Int) : Boolean {
        mActivity = activity
        mPermissions = permissions
        mPermissionRequset = PERMISSION_REQUEST
        return true
    }

    override fun requestPermissions() : Boolean {
        ActivityCompat.requestPermissions(mActivity, mPermissions, mPermissionRequset)
        return true
    }

    override fun hasPermissions(): Boolean {
        for (permission in mPermissions) {
            if (ActivityCompat.checkSelfPermission(mActivity,
                    permission) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    override fun checkVersion() : Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.e(TAG, "Version Over 13")
            return true
        }
        else {
            Log.e(TAG, "Version Under 13")
            return false
        }
    }

    override fun checkOverlay() : Boolean {
        var returnValue = false
        if (Settings.canDrawOverlays(mActivity)) {
            // 권한이 허용됨
            returnValue = true
        } else {
            // 권한이 거부됨
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$mActivity.packageName")
            )
            mActivity.startActivityForResult(intent, 1001)
            returnValue = false
        }
        return returnValue
    }

    // 권한 체크 이후로직
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grandResults: IntArray) : Boolean {
        var returnValue = false
        if (requestCode == 1000) {

            Log.e(TAG, "requestCode is 1000")

            for (i in permissions) {
                Log.e(TAG, "permission - 1 : $i")
            }
            for (i in grandResults) {
                Log.e(TAG, "permission - 2 : $i")
            }

            // 모든 퍼미션을 허용했는지 체크
            if(grandResults.all {
                it == PackageManager.PERMISSION_GRANTED }) {
                returnValue = true
            }
            else {
                val shouldRetry = permissions.any {
                    mActivity.shouldShowRequestPermissionRationale(it!!)
                }
                if(shouldRetry) {
                    // 사용자가 권한 요청을 거부했을 때 권한 재요청을 실행할 코드
                    Log.e(TAG, "shouldRetry is true")
                    Toast.makeText(mActivity, "접근 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
//                    requestPermissions(permission2, 1000)
                } else {
                    Log.e(TAG, "shouldRetry is false")
                    val actionSnackbar = Snackbar.make(snackBarContainer, "접근 권한이 거부되었습니다.", Snackbar.LENGTH_INDEFINITE)
                    actionSnackbar.setAction("설정"){
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts(
                            "package",
                            mActivity.packageName,
                            null
                        )
                        intent.data = uri
                        mActivity.startActivity(intent)
                    }
                    setSnackBarOption(actionSnackbar)
                    actionSnackbar.show()
                }
            }
        }
        else {
            Log.e(TAG, "requestCode is not 1000")
        }
        return returnValue
    }

    fun Context.dpToPx(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    // 스낵바 옵션 설정
    private fun setSnackBarOption(snackBar: Snackbar) {

//        snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE // 애니메이션 설정
//        snackBar.setActionTextColor(Color.WHITE) // 액션 버튼 색 지정
//        snackBar.setTextColor(Color.WHITE) // 안내 텍스트 색 지정
//        snackBar.setBackgroundTint(Color.BLUE) // 백그라운드 컬러 지정

        val snackBarView = snackBar.view
        val snackBarText = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        val snackBarLayout = snackBarView.layoutParams as FrameLayout.LayoutParams

        snackBarLayout.gravity = Gravity.BOTTOM // 레이아웃 위치 조정
        snackBarLayout.bottomMargin = mActivity.dpToPx(50)
        snackBar.view.layoutParams = snackBarLayout
//        snackBarLayout.width = 800 // 너비 조정
//        snackBarLayout.height = 130 // 높이 조정
//        snackBarText.textAlignment = View.TEXT_ALIGNMENT_CENTER // 안내 텍스트 위치 조정
//        snackBarText.typeface = Typeface.createFromAsset(this.assets, "context.ttf") // 폰트 지정
    }
}