package com.roh.dogdom.data.bottommenu

import android.app.Activity
import android.view.View
import androidx.navigation.NavController

interface BottomNavigationRepository {
    fun initBottomNavigation(activity: Activity ,view: View, navController: NavController)
    fun setBottomNavigation()
}