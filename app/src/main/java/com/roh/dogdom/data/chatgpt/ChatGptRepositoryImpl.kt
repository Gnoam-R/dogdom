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
    private lateinit var mResponse: retrofit2.Response<ChatGptResponse>

    private var callback: ResponseChatGptListener? = null

    override fun setCompletionCallback(callback: ResponseChatGptListener?) {
        this.callback = callback
    }

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
                if(response.isSuccessful) {
                    mResponse = response
//                    result = response.body()!!
                    val message = mResponse.body()?.choices?.get(0)?.message?.content
                    Log.e("HomeFragment", "response : ${message}")
                    callback?.onResponseDoneChatGpt(mResponse.body())
                }
            }
            override fun onFailure(call: retrofit2.Call<ChatGptResponse>, t: Throwable) {
                Log.e("HomeFragment", "t : $t")
                callback?.onResponseFailChatGpt(t)
            }
        })
    }

    override fun responseChatGpt() : ChatGptResponse {
        val result = mResponse.body()!!
        return result
    }

    override fun initChatGpt() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }
}