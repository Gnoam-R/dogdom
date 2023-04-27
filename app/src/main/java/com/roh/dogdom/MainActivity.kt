package com.roh.dogdom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.roh.dogdom.data.base.BaseActivity
import com.roh.dogdom.databinding.ActivityMainBinding
import com.roh.dogdom.module.AppModule.provideTOdoDatabase
import com.roh.dogdom.navigator.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
    }

    private fun initNavigation() {
        // navController 설정
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        val navController : NavController = navHostFragment.findNavController()
        setBottomNavigation(binding.root, navController)

    }

//    private fun bottomNavigation() {
//        // BottomNavigation 사용
//        binding.mainBottomNavigation.setupWithNavController(navController)
//        binding.mainBottomNavigation.itemIconTintList = null
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.bottom_nav_1 || destination.id == R.id.bottom_nav_2 || destination.id == R.id.bottom_nav_3 || destination.id == R.id.bottom_nav_4) {
//                binding.mainBottomNavigation.visibility = View.VISIBLE
//            } else {
//                binding.mainBottomNavigation.visibility = View.GONE
//            }
//        }
//
//
//        // BottomNavigation 사용
//
//    }

    private fun setBottomNavigation(view: View, navController : NavController) {
        // 바텀 네비게이션 && jetpack 네비게이션
        binding.bottomNavigation.setupWithNavController(navController)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // 기본 아이콘 틴트 색상 제거
        bottomNavigationView.itemIconTintList = null
//        bottomNavigationView.selectedItemId = R.id.empty

//        // 네비게이션 카메라 ui 설정
//        val item1Gif = view.findViewById<ImageView>(R.id.bt_nav_CameraButton)
//        Glide.with(this)
//            .asGif()
//            .load(R.drawable.bt_nav_camera)
//            .into(item1Gif)

        bottomNavigationView.setOnItemSelectedListener  { MenuItem ->
            when(MenuItem.itemId) {
                R.id.main_nav_host -> {
                    true
                }
                R.id.main_circle -> {
//                    navController.navigate(R.id.action_masterGoogleMapFragment_to_reQuest_mission_fragment)
                    true
                }
                R.id.main_message -> {
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