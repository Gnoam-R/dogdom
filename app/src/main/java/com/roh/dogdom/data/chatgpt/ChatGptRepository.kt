package com.roh.dogdom.data.chatgpt

import com.roh.dogdom.api.chatGpt.ChatGptResponse

interface ChatGptRepository {
    fun requestChatGpt(question: String)
    fun responseChatGpt() : ChatGptResponse
    fun initChatGpt()
    fun setCompletionCallback(callback: ResponseChatGptListener?)
}