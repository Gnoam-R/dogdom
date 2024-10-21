package com.roh.dogdom.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.roh.dogdom.util.PreferenceUtil
import com.roh.dogdom.util.enumUiColorPos

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : Fragment() {
    lateinit var binding: B
    protected lateinit var thisContext: Context
    lateinit var mContext : Context
    lateinit var mActivity : Activity
    lateinit var pref: PreferenceUtil

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
        pref = PreferenceUtil(mContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        thisContext = inflater.context
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        init()
    }

    abstract fun init()

    protected fun showToast(msg: String) =
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    protected fun setSystemStatusBarLayout() {
        // 시스템바와 싱테 표시줄 모두 layout으로 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = mActivity.window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun clearSystemStatusBarLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = mActivity.window
            w.clearFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun SystemUiChangeColor(ChPos : enumUiColorPos) {
        val view : View = mActivity.window.decorView
        // 시스템 UI 색상 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                mActivity.window.apply {
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
}