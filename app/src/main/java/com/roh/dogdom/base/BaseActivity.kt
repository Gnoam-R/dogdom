package com.roh.dogdom.base

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.roh.dogdom.util.enumUiColorPos

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : AppCompatActivity() {
    lateinit var binding: B
//    private val compositeDisposable = CompositeDisposable()
//    lateinit var lottieDialog: LottieDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)      // 다크 모드 비활성화
//        setSystemStatusBarLayout()
//        SystemUiChangeColor(enumUiColorPos.navigationUiBarBlack,this)
//        lottieDialog = LottieDialogFragment.newInstance()
    }

    protected fun setSystemStatusBarLayout() {
        // 시스템바와 싱테 표시줄 모두 layout으로 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.statusBarColor = 0x00000000  // transparent
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            val flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//            window.addFlags(flags)
//        }
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    private fun clearSystemStatusBarLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.clearFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun SystemUiChangeColor(ChPos : enumUiColorPos, activity : Activity) {
        val view : View = activity.window.decorView
        // 시스템 UI 색상 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                activity.window.apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)    // FLAG_TRANSLUCENT_STATUS 제거
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)   // windowTranslucentStatus 속성을 false
                    when(ChPos) {
                        enumUiColorPos.totalUiBarWhite -> { // 상채바 및 내비게이션 UI 색상 변경 흰색
                            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                        }
                        enumUiColorPos.totalUiBarBlack -> { // 상채바 및 내비게이션 UI 색상 변경 검은색
                            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                        }
                        enumUiColorPos.statusUiBarWhite -> { // 상태바 UI 만 변경
                            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                        }
                        enumUiColorPos.navigationUiBarWhite -> { // 내비게이션 UI 만 변경
                            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        }
                        enumUiColorPos.statusUiBarBlack -> { // 상태바 UI 만 변경
                            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        }
                        enumUiColorPos.navigationUiBarBlack -> { // 내비게이션 UI 만 변경
                            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                        }
                    }
                }
            }
        }else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
        }
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
//        compositeDisposable.clear()
    }

    fun showProgressDialog() {
//        lottieDialog.show(
//            this.supportFragmentManager,
//            lottieDialog.tag
//        )
    }

    fun hideProgressDialog() {
//        if (lottieDialog.isAdded) {
//            lottieDialog.dismissAllowingStateLoss()
//        }
    }
}