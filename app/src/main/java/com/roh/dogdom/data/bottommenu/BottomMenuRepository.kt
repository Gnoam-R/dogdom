package com.roh.dogdom.data.bottommenu

import android.view.View
import androidx.navigation.NavController

interface BottomMenuRepository {
    fun initBottomNavigation(view: View, navController: NavController)
    fun setBottomNavigation()
}