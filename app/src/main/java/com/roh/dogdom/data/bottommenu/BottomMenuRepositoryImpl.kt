package com.roh.dogdom.data.bottommenu

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.roh.dogdom.R
import com.roh.dogdom.views.login.LoginFragmentDirections

class BottomMenuRepositoryImpl() : BottomMenuRepository {

    private lateinit var mActivity : Activity
    private lateinit var mView : View
    private lateinit var mNavController : NavController
    private lateinit var mNavView : BottomNavigationView

    override fun initBottomNavigation(activity : Activity, view: View, navController: NavController){
        mActivity = activity
        mView = view
        mNavController = navController
        mNavView = mView.findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // navigation view를 보여줄 fragment를 추가
        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.masterMainFragment -> {
                    mNavView.visibility = View.VISIBLE
                }
                R.id.messagesFragment -> {
                    mNavView.visibility = View.VISIBLE
                }
                else -> {
                    mNavView.visibility = View.GONE
                }
            }
        }
    }

    override fun setBottomNavigation() {
        // 바텀 네비게이션 && jetpack 네비게이션
        Log.e("BottomMenuRepositoryImpl", "setBottomNavigation")
        mNavView.setupWithNavController(mNavController)
        // 기본 아이콘 틴트 색상 제거
        mNavView.itemIconTintList = null
        mNavView.setOnItemSelectedListener  { MenuItem ->
            when(MenuItem.itemId) {
                R.id.main_home -> {
                    Log.e("BottomMenuRepositoryImpl", "main_home")
                    val direction: NavDirections = LoginFragmentDirections.actionLoginFragmentToMasterMainFragment()
                    mNavController.navigate(direction)
                    true
                }
                R.id.main_circle -> {
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_reQuest_mission_fragment)
                    Log.e("BottomMenuRepositoryImpl", "main_circle")
                    true
                }
                R.id.main_release -> {
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_giftShop_fragment)
                    Log.e("BottomMenuRepositoryImpl", "main_release")
                    true
                }
                R.id.main_message -> {
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_myPage_fragment)
                    Log.e("BottomMenuRepositoryImpl", "main_message")
                    val direction: NavDirections = LoginFragmentDirections.actionLoginFragmentToMessagesFragment()
                    mNavController.navigate(direction)
                    true
                }
                R.id.main_user -> {
                    Log.e("BottomMenuRepositoryImpl", "main_user")
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_myPage_fragment)
                    true
                }
                else -> {
                    Log.e("BottomMenuRepositoryImpl", "false")
                    false
                }
            }
        }
    }
}