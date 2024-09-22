package com.roh.dogdom.views.startup
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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

            goEx.observe(this@MainActivity, Observer {
                goEx()
            })
        }
    }
}