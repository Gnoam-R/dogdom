package com.roh.dogdom.data.dogimage

import com.roh.dogdom.api.chatGpt.ChatGptResponse

interface DogImageRepository {
    fun requestDogAPi()
//    fun responseChatGpt() : ChatGptResponse
    fun initDogApi()
//    fun setCompletionCallback(callback: ResponseChatGptListener?)
}