package com.roh.dogdom.data.chatgpt

import com.roh.dogdom.api.chatGpt.ChatGptResponse

interface ResponseChatGptListener {
    fun onResponseDoneChatGpt(chatGptResponse: ChatGptResponse?)
    fun onResponseFailChatGpt(t: Throwable)
}