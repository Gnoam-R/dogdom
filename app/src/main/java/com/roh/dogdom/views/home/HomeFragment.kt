package com.roh.dogdom.views.home

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.roh.dogdom.R
import com.roh.dogdom.api.chatGpt.ChatMessage
import com.roh.dogdom.api.chatGpt.RetrofitClient
import com.roh.dogdom.api.chatGpt.RetrofitService
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.db.log.LoggerLocalDataSource
import com.roh.dogdom.data.db.user.UserLocalDataSource
import com.roh.dogdom.data.firebase.comment.CommentRepository
import com.roh.dogdom.data.firebase.like.LikeRepository
import com.roh.dogdom.data.firebase.post.PostRepository
import com.roh.dogdom.data.firebase.user.UserRepository
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.databinding.FragmentHomeBinding
import com.roh.dogdom.navigator.AppNavigator
import com.roh.dogdom.navigator.Screens
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.log.ButtonsFragment
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home){
    @Inject lateinit var navigator: AppNavigator

    private val viewModel by viewModels<HomeViewModel>()

    private val homeContentsFirstFragment = HomeContentsFirstFragment()
    private val homeContentsSecondFragment = HomeContentsSecondFragment()
    private val homeSearchFragment = HomeSearchFragment()
    private val buttonsFragment = ButtonsFragment()

    private lateinit var retrofitService: RetrofitService
    private lateinit var retrofit : Retrofit
//    lateinit var messages: MutableList<ChatMessage>

    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        clearSystemStatusBarLayout()
        replaceFragment(1, homeContentsFirstFragment)
        replaceFragment(2, homeContentsSecondFragment)
        initViewModelCallback()
        initRetrofit()

        binding.btTgAlarm.setOnClickListener {
            replaceFragment(3, buttonsFragment)
            navigator.navigateTo(Screens.BUTTONS)
        }
        binding.transformationLayout.setOnClickListener {
            binding.transformationLayout.startTransform()
            replaceFragment(3, homeSearchFragment)
        }
        binding.btTgDiscover.setOnClickListener {}
    }

    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    private fun replaceFragment(fragmentNum : Int, fragment: Fragment) {
        when(fragmentNum){
            1 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.main_fragment_container1, fragment)
                    commit()
                }
            }
            2 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.ct_home_second, fragment)
                    commit()
                }
            }
            3 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.fg_search_onclick, fragment)
                    commit()
                }
            }
        }
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goEx.observe(viewLifecycleOwner, Observer {
                Log.e("MasterMainViewModel", "goEx")
                goEx()
            })
        }
    }
}

