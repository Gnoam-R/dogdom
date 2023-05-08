package com.roh.dogdom.views.startup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseActivity
import com.roh.dogdom.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    private var navHostFragment: Fragment? = null
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavHost()
        initBottomMenu()
//        setBottomNavigation()
        initViewModelCallback()
    }

    private fun initNavHost() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host)
        navController = navHostFragment!!.findNavController()
    }

    private fun initBottomMenu() {
        viewModel.initBottomMenu(activity = this, binding.root, navController)
        viewModel.setBottomMenu()
    }

    private fun initViewModelCallback() {

        with(viewModel) {
//            goMain.observe(viewLifecycleOwner, Observer {
//                val direction: NavDirections = com.roh.dogdom.views.login.LoginFragmentDirections.actionLoginFragmentToMasterMainFragment()
//                findNavController().navigate(direction)
//            })
//
            goEx.observe(this@MainActivity, Observer {
                Log.e("MainActivity", "goEx")
                goEx()
            })
        }
    }

    fun setBottomNavigation() {
        // 바텀 네비게이션 && jetpack 네비게이션

        binding.bottomNavigation.setupWithNavController(navController)
        // 기본 아이콘 틴트 색상 제거
        binding.bottomNavigation.itemIconTintList = null
        binding.bottomNavigation.visibility = View.VISIBLE
        binding.bottomNavigation.setOnItemSelectedListener  { MenuItem ->
            when(MenuItem.itemId) {
                R.id.main_nav_host -> {
                    Log.e("BottomMenuRepositoryImpl", "main_nav_host")
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
                    true
                }
                R.id.main_user -> {
                    Log.e("BottomMenuRepositoryImpl", "${MenuItem.itemId}")
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_myPage_fragment)
                    true
                }
                else -> {
                    Log.e("BottomMenuRepositoryImpl", "${MenuItem.itemId}")
                    false
                }

            }
        }
    }
}