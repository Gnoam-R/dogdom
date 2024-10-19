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
import com.roh.dogdom.data.db.release.local.ReleaseLocalDataSource
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

    // retrofit2 test
    private lateinit var retrofitService: RetrofitService
    private lateinit var retrofit : Retrofit

    @Inject lateinit var logDb: LoggerLocalDataSource
    @Inject lateinit var navigator: AppNavigator

    @Inject lateinit var commentRepository: CommentRepository
    @Inject lateinit var likeRepository: LikeRepository
    @Inject lateinit var postRepository: PostRepository
    @Inject lateinit var userRepository: UserRepository

    private lateinit var homeAdapter : HomeAdapter
    private lateinit var mainPost : MainPost

    lateinit var messages: MutableList<ChatMessage>

    private val viewModel by viewModels<HomeViewModel>()

    private val homeContentsFirstFragment = HomeContentsFirstFragment()
    private val homeContentsSecondFragment = HomeContentsSecondFragment()
    private val homeSearchFragment = HomeSearchFragment()
    private val buttonsFragment = ButtonsFragment()

   lateinit var fbStorage : FirebaseStorage

    private fun downLoadFB() {
        // Create a storage reference from our app
        val storageRef = fbStorage.reference

        // Create a reference with an initial file path and name
        val pathReference = storageRef.child("0604_file/0604_image1")

        // Create a reference to a file from a Google Cloud Storage URI
        val gsReference = fbStorage.getReferenceFromUrl("gs://bucket/images/stars.jpg")

        // Create a reference from an HTTPS URL
        // Note that in the URL, characters are URL escaped!
        val httpsReference = fbStorage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg",
        )
    }
    private fun initFB() {
        commentRepository.init()
        likeRepository.init()
        postRepository.init()
        userRepository.init()
    }

    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        clearSystemStatusBarLayout()
        replaceFragment(1, homeContentsFirstFragment)
        replaceFragment(2, homeContentsSecondFragment)
        initViewModelCallback()
        initRetrofit()
        initFB()

        Log.e("HomeFragment","${findNavController().currentDestination?.id}")

        binding.btTgAlarm.setOnClickListener {
            replaceFragment(3, buttonsFragment)
            navigator.navigateTo(Screens.BUTTONS)
        }
        binding.transformationLayout.setOnClickListener {
            binding.transformationLayout.startTransform()
            replaceFragment(3, homeSearchFragment)
        }
        binding.btTgDiscover.setOnClickListener {
            logDb.removeLogs()
        }
        val etQuestion = binding.etSearch

    }

    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
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

