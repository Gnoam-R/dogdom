package com.roh.dogdom.data.chatgpt

import android.util.Log
import com.roh.dogdom.api.*
import retrofit2.Callback
import retrofit2.Retrofit

class ChatGptRepositoryImpl : ChatGptRepository{

    // retrofit2 test
    private lateinit var retrofitService: RetrofitService
    private lateinit var retrofit : Retrofit

    override fun requestChatGpt(question: String) {
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

    override fun initChatGpt() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }
}