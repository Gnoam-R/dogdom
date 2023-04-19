package com.roh.dogdom.views.main

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.data.base.BaseFragment
import com.roh.dogdom.data.main.MainPost
import com.roh.dogdom.databinding.FragmentMasterMainBinding
import com.roh.dogdom.navigator.AppNavigator
import com.roh.dogdom.navigator.Screens
import com.roh.dogdom.views.log.ButtonsFragment
import com.roh.dogdom.views.splash.SplashFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MasterMainFragment : BaseFragment<FragmentMasterMainBinding>(R.layout.fragment_master_main){

    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var masterMainAdapter : MasterMainAdapter
    private lateinit var mainPost : MainPost

    private val masterMainContentsFirstFragment = MasterMainContentsFirstFragment()
    private val masterMainContentsSecondFragment = MasterMainContentsSecondFragment()
    private val buttonsFragment = ButtonsFragment()

    override fun init() {
        replaceFragment(1, masterMainContentsFirstFragment)
        replaceFragment(2, masterMainContentsSecondFragment)

        binding.btTgAlarm.setOnClickListener {
//            replaceFragment(3, buttonsFragment)
            navigator.navigateTo(Screens.BUTTONS)
        }
    }

    private fun moveNextScreen() {
//        findNavController().navigate(
//            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        )
    }
    private fun replaceFragment(fragmentNum : Int, fragment: Fragment) {
        // 현 Activity 에 연결된 Fragment 관리하는 supportFragmentManager 를 통해 Fragment 전환
        when(fragmentNum){
            1 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.main_fragment_container1, fragment)
                    commit()
                }
            }
            2 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.main_fragment_container2, fragment)
                    commit()
                }
            }
            3 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.main_fragment_container2, fragment)
                    commit()
                }
            }
        }
    }
}