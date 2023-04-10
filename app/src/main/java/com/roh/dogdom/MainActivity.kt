package com.roh.dogdom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.roh.dogdom.base.BaseActivity
import com.roh.dogdom.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
    }

    private fun initNavigation() {
//        val navController = findNavController(R.id.main_nav_host)

        // BottomNavigation 사용
//        binding.mainBottomNavigation.setupWithNavController(navController)
//        binding.mainBottomNavigation.itemIconTintList = null

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.bottom_nav_1 || destination.id == R.id.bottom_nav_2 || destination.id == R.id.bottom_nav_3 || destination.id == R.id.bottom_nav_4) {
//                binding.mainBottomNavigation.visibility = View.VISIBLE
//            } else {
//                binding.mainBottomNavigation.visibility = View.GONE
//            }
//        }
    }
}