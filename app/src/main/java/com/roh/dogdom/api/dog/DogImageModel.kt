package com.roh.dogdom.api.dog

import com.google.gson.annotations.SerializedName
import com.roh.dogdom.api.chatGpt.ChatMessage

data class DogImageRespond (
    @SerializedName("model") val model: String,
    @SerializedName("status") val status: String,
)