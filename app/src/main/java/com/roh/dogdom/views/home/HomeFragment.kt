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

    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var homeAdapter : HomeAdapter
    private lateinit var mainPost : MainPost

    lateinit var messages: MutableList<ChatMessage>


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

        initRetrofit()

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

            if(question != "")
                getSearchList(question)

            //okttp3로 하는 방법
//            chatGptControll(question) { response ->
//                mActivity.runOnUiThread{
//                    Log.e("HomeFragment", "response : $response")
//                }
//            }

        }
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

    // okttp3 로 하는 방법
    fun chatGptControll(question: String, param: (Any) -> Unit) {
//        val apikey = "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"
//        val apikey2 = "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"
        val godskey = "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"
//        val url = "https://api.openai.com/v1/chat/completions"
        val url = "https://api.openai.com/v1/chat/completions"

        val requestBody = """
            {
            "model": "gpt-3.5-turbo",
            "messages": [
                {
                    "role": "user",
                    "content": "$question"
                }
            ],
            "temperature": 1,
            "max_tokens": 512,
            "top_p": 1,
            "frequency_penalty": 0,
            "presence_penalty": 0 
            }
        """.trimIndent()


        Log.e("HomeFragment", "requestBody : $question")

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type","application/json")
            .addHeader("Authorization",  "Bearer $godskey")
            .post(requestBody.toRequestBody())
            .build()

//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
//                Log.e("HomeFragment", "error : ${e.message}")
//            }
//
//            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
//                val body = response.body?.string()
//                if(body != null){
//                    Log.e("HomeFragment", "body : $body")
//                }
//                else
//                    Log.e("HomeFragment", "body is null")
//
//                val jsonObject = JSONObject(body!!)
//                val jsonArray:JSONArray=jsonObject.getJSONArray("choices")
//                Log.e("HomeFragment", "jsonArray : $jsonArray")
//                val textResult = jsonArray.getJSONObject(0).getString("message")
//                Log.e("HomeFragment", "textResult : $textResult")
//            }
//        })

    }

//    public data class ChatMessage(val role: String, val content: String) {
//        constructor(json: JsonObject) : this(
//            json["role"].asString,
//            json["content"].asString
//        )
//    }

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

