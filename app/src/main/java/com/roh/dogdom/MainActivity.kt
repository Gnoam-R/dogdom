package com.roh.dogdom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.base.BaseActivity
import com.roh.dogdom.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
    }

    private fun initNavigation() {
        // navController 설정
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        val navController : NavController = navHostFragment.findNavController()
    }

    private fun bottomNavigation() {
        /*// BottomNavigation 사용
        binding.mainBottomNavigation.setupWithNavController(navController)
        binding.mainBottomNavigation.itemIconTintList = null

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.bottom_nav_1 || destination.id == R.id.bottom_nav_2 || destination.id == R.id.bottom_nav_3 || destination.id == R.id.bottom_nav_4) {
                binding.mainBottomNavigation.visibility = View.VISIBLE
            } else {
                binding.mainBottomNavigation.visibility = View.GONE
            }
        }
         */
    }

}