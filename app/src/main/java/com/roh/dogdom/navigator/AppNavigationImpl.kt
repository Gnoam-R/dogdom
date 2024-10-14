package com.roh.dogdom.navigator

import androidx.fragment.app.FragmentActivity
import com.roh.dogdom.R
import com.roh.dogdom.views.home.HomeFragment
import com.roh.dogdom.views.log.ButtonsFragment
import com.roh.dogdom.views.log.LogsFragment
import javax.inject.Inject

/**
 * TODO :Navigator implementation.
 */

class AppNavigationImpl @Inject constructor(private val activity: FragmentActivity) : AppNavigator {
    override fun navigateTo(screen: Screens) {
        val fragment = when (screen) {
            Screens.HOME -> HomeFragment()
            Screens.BUTTONS -> ButtonsFragment()
            Screens.LOGS -> LogsFragment()
        }

        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container2, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
    }
}