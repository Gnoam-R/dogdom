package com.roh.dogdom.data.bottommenu

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.roh.dogdom.R
import com.roh.dogdom.data.navigation.*


class BottomMenuRepositoryImpl() : BottomMenuRepository {

    private lateinit var mActivity : Activity
    private lateinit var mView : View
    private lateinit var mNavController : NavController
    private lateinit var mNavView : BottomNavigationView

    private lateinit var currentFragmentId : CurrentFragmentId
    private lateinit var nextFragmentId : NextFragmentId
    private lateinit var mvFragment : MvFragment

    private var mNavCurrentPos : Int = -1

    override fun initBottomNavigation(activity : Activity, view: View, navController: NavController){
        mActivity = activity
        mView = view
        mNavController = navController
        mNavView = mView.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        currentFragmentId = CurrentFragmentId()
        nextFragmentId = NextFragmentId()
        mvFragment = MvFragment()

        // navigation view를 보여줄 fragment를 추가
        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    mNavView.visibility = View.VISIBLE
                }
                R.id.messageFragment -> {
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
        val direction : NavDirections = ActionOnlyNavDirections(actionId = R.id.messagesFragment)
        Log.e("setBottomNavigation", "${direction}")

        mNavView.setOnItemSelectedListener  { MenuItem ->
//            mNavCurrentPos = mNavController.currentDestination!!.id
            when(MenuItem.itemId) {
                R.id.main_home -> {
                    checkFragmentId(nextFragmentId.HOME)
                    true
                }
                R.id.main_circle -> {
                    checkFragmentId(nextFragmentId.HOME)
                    true
                }
                R.id.main_release -> {
                    checkFragmentId(nextFragmentId.HOME)
                    true
                }
                R.id.main_message -> {
                    mNavController.navigate(R.id.action_homeFragment_to_messageFragment)
//                    checkFragmentId(nextFragmentId.MESSAGE)
                    true
                }
                R.id.main_user -> {
                    checkFragmentId(nextFragmentId.LOGIN)
                    true
                }
                else -> {
                    Log.e("setBottomNavigation", "false")
                    false
                }
            }
        }
    }

    private fun checkFragmentId(mNavNextPos: Int) {

        var nextFragment : Int = -1
        var currentFragment : Int = -1
        var mvFragment : Int = -1

        when (mNavNextPos) {
            nextFragmentId.HOME -> nextFragment = 1
            nextFragmentId.CIRCLE -> nextFragment = 2
            nextFragmentId.RELEASE -> nextFragment = 3
            nextFragmentId.MESSAGE -> nextFragment = 4
            nextFragmentId.USER -> nextFragment = 5
            nextFragmentId.LOGIN -> nextFragment = 6
            else -> Log.e("checkFragmentId", "check next Fragment Id again")
        }
        mvFragment = nextFragment
        moveFragment(mvFragment)
//        if(currentFragment == nextFragment)
//            Log.e("checkFragmentId", "locate current fragement")
//        else {
//            mvFragment = nextFragment
//            moveFragment(mvFragment)
//        }
    }

    private fun moveFragment(nextFragment : Int) {
        val direction: NavDirections = when (nextFragment) {
            nextFragmentId.HOME -> ActionOnlyNavDirections(actionId= mvFragment.HomeId)
            nextFragmentId.CIRCLE -> ActionOnlyNavDirections(actionId= mvFragment.HomeId)
            nextFragmentId.MESSAGE -> ActionOnlyNavDirections(actionId= mvFragment.MessageId)
            nextFragmentId.USER -> ActionOnlyNavDirections(actionId= mvFragment.HomeId)
            nextFragmentId.RELEASE -> ActionOnlyNavDirections(actionId= mvFragment.HomeId)
            nextFragmentId.LOGIN -> ActionOnlyNavDirections(actionId= mvFragment.Loginid)
            else -> ActionOnlyNavDirections(actionId= mvFragment.HomeId)
        }
        mNavController.navigate(direction)
    }
}