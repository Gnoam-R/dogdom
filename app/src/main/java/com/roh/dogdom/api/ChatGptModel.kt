package com.roh.dogdom.api

import com.google.gson.annotations.SerializedName

data class ChatGptRequest(
    @SerializedName("model") val model: String,
    @SerializedName("messages") val messages: List<ChatMessage>,
    @SerializedName("temperature") val temperature: Int,
    @SerializedName("max_tokens") val max_tokens: Int,
    @SerializedName("top_p") val top_p: Int,
    @SerializedName("frequency_penalty") val frequency_penalty: Int,
    @SerializedName("presence_penalty") val presence_penalty: Int,
)

data class ChatGptResponse(
    @SerializedName("id") val id: String,
    @SerializedName("object") val objectName: String,
    @SerializedName("created") val created: Double,
    @SerializedName("model") val model: String,
    @SerializedName("usage") val usage: ChatUsages,
    @SerializedName("choices") val choices: List<ChatChoice>
)

data class ChatMessage(
    @SerializedName("role") val role: String,
    @SerializedName("content") val content: String
)
data class ChatUsages(
    @SerializedName("prompt_tokens") val prompt_tokens: Int,
    @SerializedName("completion_tokens") val completion_tokens: Int,
    @SerializedName("total_tokens") val total_tokens: Int
)
data class ChatChoice(
    @SerializedName("message") val message: ChatMessage,
    @SerializedName("finish_reason") val finish_reason: String,
    @SerializedName("index") val index: Int
)


//data class ChatGptRequest (
////    @SerializedName("choices") private val choices: List<ChatChoice>? = null
//
//    @SerializedName("message")val message: String,
//    @SerializedName("role")val role: String,
//    @SerializedName("finish_reason")val choices: List<ChatChoice>
//    )