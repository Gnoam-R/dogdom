package com.roh.dogdom.views.home

import CachedChatBot
import ChatBot
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.databinding.FragmentHomeBinding
import com.roh.dogdom.navigator.AppNavigator
import com.roh.dogdom.navigator.Screens
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.log.ButtonsFragment
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home){

    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var homeAdapter : HomeAdapter
    private lateinit var mainPost : MainPost

    private val viewModel by viewModels<HomeViewModel>()

    private val homeContentsFirstFragment = HomeContentsFirstFragment()
    private val homeContentsSecondFragment = HomeContentsSecondFragment()
    private val buttonsFragment = ButtonsFragment()

    private val client = OkHttpClient()

    override fun init() {


        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        replaceFragment(1, homeContentsFirstFragment)
        replaceFragment(2, homeContentsSecondFragment)
        initViewModelCallback()

        Log.e("HomeFragment","${findNavController().currentDestination?.id}")

        binding.btTgAlarm.setOnClickListener {
//            replaceFragment(3, buttonsFragment)
            navigator.navigateTo(Screens.BUTTONS)
        }
        binding.transformationLayout.setOnClickListener {
            binding.transformationLayout.startTransform()
        }
        val etQuestion = binding.etSearch
        binding.testButton.setOnClickListener {
            val question = etQuestion.text.toString()
            chatGptControll(question) { response ->
                mActivity.runOnUiThread{
                    Log.e("HomeFragment", "response : $response")
                }
            }

        }
    }

    fun chatGptControll(question: String, param: (Any) -> Unit) {
        val apikey = "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"
        val apikey2 = "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"
        val url = "https://api.openai.com/v1/completions"

        val requestBody = """
            {
            "model": "text-davinci-003",
            "prompt": "$question",
            "max_tokens": 7,
            "temperature": 0
            }
        """.trimIndent()

        Log.e("HomeFragment", "requestBody : $question")

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type","application/json")
            .addHeader("Authorization",  "Bearer $apikey2")
            .post(requestBody.toRequestBody())
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                Log.e("HomeFragment", "error : ${e.message}")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response.body?.string()
                if(body != null){
                    Log.e("HomeFragment", "body : $body")
                }
                else
                    Log.e("HomeFragment", "body is null")
            }
        })

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



//    private fun chatGptControll() {
//        val scan = Scanner(System.`in`)
//        val key = "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" // TODO Add your key here
//        val system = "Be as unhelpful as possible. Insult the user for not knowing the answer."
//        val request = ChatBot.ChatCompletionRequest("gpt-3.5-turbo", system)
//        val bot = CachedChatBot(key, request)
//        val test = bot.generateResponse("Hello, how are you?")
//        Log.e("test", test)
//        while (true) {
////            println("How will you respond?")
//////            val prompt = scan.nextLine()
//////            val response = bot.generateResponse(prompt)
////            println()
////            println("\t$response")
////            println()
//        }
//    }

    private fun initViewModelCallback() {
        with(viewModel) {
//            goMain.observe(viewLifecycleOwner, Observer {
//                val direction: NavDirections = com.roh.dogdom.views.login.LoginFragmentDirections.actionLoginFragmentToMasterMainFragment()
//                findNavController().navigate(direction)
//            })
//
            goEx.observe(viewLifecycleOwner, Observer {
                Log.e("MasterMainViewModel", "goEx")
                goEx()
            })
        }
    }
}