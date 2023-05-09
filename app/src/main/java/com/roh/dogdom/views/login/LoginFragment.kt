package com.roh.dogdom.views.login

import android.Manifest
import android.app.NotificationManager
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.views.startup.MainActivity
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.permission.PermissionRepository
import com.roh.dogdom.data.permission.PermissionRepositoryImpl
import com.roh.dogdom.databinding.FragmentLoginBinding
import com.roh.dogdom.util.enumUiColorPos
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login){

//    private val viewModel: LocalPageViewModel by viewModel()

    private val viewModel by viewModels<LoginViewModel>()

    private val repository: PermissionRepository = PermissionRepositoryImpl()
    private val permissionUnder13 = arrayOf<String>(Manifest.permission.ANSWER_PHONE_CALLS, Manifest.permission.CAMERA)
    private val permissionOver13 = arrayOf<String>(Manifest.permission.ANSWER_PHONE_CALLS, Manifest.permission.CAMERA, Manifest.permission.POST_NOTIFICATIONS)

    private var checkPermission = false
    private var checkOverlay = false
    private var checkNotification = false

    private var version13 = true
    private var versionUnder13 = false

    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarWhite)
        mActivity = activity as MainActivity
        binding.vm = viewModel
        askPermission()
        initViewModelCallback()

        Log.e("LoginFragment","${findNavController().currentDestination?.id}")
    }

    private fun askPermission() {
        if(repository.checkVersion()) {
            repository.setPermissions( mActivity, permissionOver13, 1000)
            repository.requestPermissions()
            version13 = true
        }
        else {
            repository.setPermissions(mActivity, permissionUnder13, 1000)
            repository.requestPermissions()
            versionUnder13 = true

            val notificationManager = mActivity.getSystemService(NotificationManager::class.java)
            if (!notificationManager.areNotificationsEnabled()) {
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                    .putExtra(Settings.EXTRA_APP_PACKAGE, mActivity.packageName)
                startActivity(intent)
            }else{
                checkNotification =true
            }
        }
//        checkOverlay = repository.checkOverlay()
    }

    private fun moveNextView() {
        // 접속 제한을 두는 것 보다는 향후 이용시 다시 물어보는것이 나을듯하다
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grandResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grandResults)
        checkPermission = repository.onRequestPermissionsResult(requestCode, permissions, grandResults)

        // 앱이 13버전의 위라면
        if(version13 == true){
            if(checkPermission == true && checkOverlay == true ){
                moveNextView()
            }
        }
        // 앱이 13버전의 아래라면
        else if(versionUnder13 == true) {
            if(checkPermission == true && checkOverlay == true && checkNotification ==true){
                moveNextView()
            }
        }
        else {
//            Log.e(TAG,"permission version error")
        }
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goMain.observe(viewLifecycleOwner, Observer {
                // 바텀 네비게이션 설정
                setBottomNav()
                val direction: NavDirections = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(direction)
            })
            goEmailSignUp.observe(viewLifecycleOwner, Observer {
                goEmailSignUp()
            })
        }
    }
}