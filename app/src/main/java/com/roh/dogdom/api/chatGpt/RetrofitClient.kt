package com.roh.dogdom.api.chatGpt

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = null
    private var gson = GsonBuilder().setLenient().create()
    // 서버 주소
    private const val BASE_URL = "https://api.openai.com"

    // SingleTon
    fun getInstance() : Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return instance!!
    }

    val request = ChatGptRequest(
        model = "gpt-3.5-turbo",
        messages = listOf(ChatMessage("user", "Say this is a test!")),
        temperature = 1,
        max_tokens = 512,
        top_p = 1,
        frequency_penalty = 0,
        presence_penalty = 0
    )
}