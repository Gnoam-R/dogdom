package com.roh.dogdom.views.startup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseActivity
import com.roh.dogdom.databinding.ActivityMainBinding
import com.roh.dogdom.views.main.MasterMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomMenu()
        initViewModelCallback()
    }

    private fun initBottomMenu() {
        viewModel.initBottomMenu(binding.root, findNavController(R.id.main_nav_host))
    }

    private fun initViewModelCallback() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host)
        val navController = navHostFragment?.findNavController()

        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.masterMainFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
            }
        }

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
}