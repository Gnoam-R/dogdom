package com.roh.dogdom.api

import android.content.Context
import com.roh.dogdom.R
import retrofit2.Call
import retrofit2.http.*

// 서버에서 호출할 메서드를 선언하는 인터페이스
// Post 방식으로 Field라는 데이터를 주고 받을 때 넘기는 변수를 사용


const val godskey = "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"

interface RetrofitService {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer $godskey"
    )
    @POST("v1/chat/completions")
    fun getChatCompletion(@Body requestBody: ChatGptRequest): Call<ChatGptResponse>
}

