package com.roh.dogdom.data.bottommenu

import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.roh.dogdom.R

class BottomMenuRepositoryImpl() : BottomMenuRepository {


    private lateinit var mView : View
    private lateinit var mNavController : NavController

    override fun initBottomNavigation(view: View, navController: NavController){
        mView = view
        mNavController = navController
    }

    override fun setBottomNavigation() {
        // 바텀 네비게이션 && jetpack 네비게이션
        Log.e("BottomMenuRepositoryImpl", "setBottomNavigation")
        val bottomNavigationView = mView.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setupWithNavController(mNavController)
        // 기본 아이콘 틴트 색상 제거
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.visibility = View.VISIBLE
        bottomNavigationView.setOnItemSelectedListener  { MenuItem ->
            when(MenuItem.itemId) {
                R.id.main_nav_host -> {
                    true
                }
                R.id.main_circle -> {
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_reQuest_mission_fragment)
                    true
                }
                R.id.main_release -> {
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_giftShop_fragment)
                    true
                }
                R.id.main_message -> {
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_myPage_fragment)
                    true
                }
                R.id.main_user -> {
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_myPage_fragment)
                    true
                }
                else -> false
            }
        }
    }
}