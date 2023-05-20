package com.roh.dogdom.data.chatgpt

import android.util.Log
import com.roh.dogdom.api.ChatGptRequest
import com.roh.dogdom.api.ChatGptResponse
import com.roh.dogdom.api.ChatMessage
import com.roh.dogdom.api.RetrofitClient
import com.roh.dogdom.api.RetrofitService
import retrofit2.Callback
import retrofit2.Retrofit

interface ChatGptRepository {
    fun requestChatGpt(question: String)
    fun initChatGpt()
}