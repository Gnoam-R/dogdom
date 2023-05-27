package com.roh.dogdom.views.home


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.roh.dogdom.R
import com.roh.dogdom.api.*
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.db.BaseDb
import com.roh.dogdom.data.db.BaseLocalDataSource
import com.roh.dogdom.data.db.Log.LoggerLocalDataSource
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.databinding.FragmentHomeBinding
import com.roh.dogdom.navigator.AppNavigator
import com.roh.dogdom.navigator.Screens
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.log.ButtonsFragment
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
//import okhttp3.Callback
import retrofit2.Callback
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home){

    // retrofit2 test
    private lateinit var retrofitService: RetrofitService
    private lateinit var retrofit : Retrofit

    @Inject lateinit var baseDb: BaseLocalDataSource


    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var homeAdapter : HomeAdapter
    private lateinit var mainPost : MainPost

    lateinit var messages: MutableList<ChatMessage>

    private val viewModel by viewModels<HomeViewModel>()

    private val homeContentsFirstFragment = HomeContentsFirstFragment()
    private val homeContentsSecondFragment = HomeContentsSecondFragment()
    private val buttonsFragment = ButtonsFragment()

    private fun initDB(aa : Int) {
        baseDb.addLog("test : ${aa}" )
        baseDb.getAllLogs { it ->
            Log.e("initDB ", it[aa].msg)
        }
    }

    var aa = 0

    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        clearSystemStatusBarLayout()
        replaceFragment(1, homeContentsFirstFragment)
        replaceFragment(2, homeContentsSecondFragment)
        initViewModelCallback()
        initRetrofit()

        Log.e("HomeFragment","${findNavController().currentDestination?.id}")

        binding.btTgAlarm.setOnClickListener {
            initDB(aa++)
//            replaceFragment(3, buttonsFragment)
//            navigator.navigateTo(Screens.BUTTONS)
        }
        binding.transformationLayout.setOnClickListener {
            binding.transformationLayout.startTransform()
        }
        val etQuestion = binding.etSearch
    }

    private fun getSearchList(question: String) {
        Log.e("HomeFragment", "getSearchList")
        retrofitService.getChatCompletion(
            requestBody = ChatGptRequest(
                model = "gpt-3.5-turbo",
                messages = listOf(ChatMessage("user", "$question")),
                temperature = 1,
                max_tokens = 512,
                top_p = 1,
                frequency_penalty = 0,
                presence_penalty = 0
            )
        ).enqueue(object : Callback<ChatGptResponse> {
            override fun onResponse(
                call: retrofit2.Call<ChatGptResponse>,
                response: retrofit2.Response<ChatGptResponse>
            ) {
                val message = response.body()?.choices?.get(0)?.message?.content
                Log.e("HomeFragment", "response : ${response}")
            }

            override fun onFailure(call: retrofit2.Call<ChatGptResponse>, t: Throwable) {
                Log.e("HomeFragment", "t : $t")
            }

        })
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

    private fun initViewModelCallback() {
        with(viewModel) {
            goEx.observe(viewLifecycleOwner, Observer {
                Log.e("MasterMainViewModel", "goEx")
                goEx()
            })
        }
    }
}

