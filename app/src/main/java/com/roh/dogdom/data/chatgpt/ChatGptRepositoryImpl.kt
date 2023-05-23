package com.roh.dogdom.data.chatgpt

import android.util.Log
import com.roh.dogdom.api.*
import retrofit2.Callback
import retrofit2.Retrofit

class ChatGptRepositoryImpl : ChatGptRepository{

    // retrofit2 test
    private lateinit var retrofitService: RetrofitService
    private lateinit var retrofit : Retrofit
    private var result : ChatGptResponse? = null
    private var aa = 0

    override fun requestChatGpt(question: String) : ChatGptResponse? {
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
                result = response.body()!!
                val message = response.body()?.choices?.get(0)?.message?.content
                Log.e("HomeFragment", "response : ${message}")
            }
            override fun onFailure(call: retrofit2.Call<ChatGptResponse>, t: Throwable) {
                Log.e("HomeFragment", "t : $t")
                result = null
            }
        })
        return result
    }

//    override fun responseChatGpt() : ChatGptResponse {
//        Log.e("HomeFragment", "responseChatGpt")
//        val message = response.body()?.choices?.get(0)?.message?.content
//    }

    override fun initChatGpt() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }
}