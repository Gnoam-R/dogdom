package com.roh.dogdom.data.chatgpt

import androidx.annotation.DrawableRes

class ChatGptInfo {
    companion object {
        val dataList: MutableList<ChatGptDataSource> = mutableListOf()
    }

    fun getData(): MutableList<ChatGptDataSource> {
        return dataList
    }

    fun addData(name: String, message: String, image: Int, viewType: Int) {
        val data = ChatGptDataSource(name, message, image, viewType)
        dataList.add(data)
    }
}


data class ChatGptDataSource(
    val stringName: String,
    val stringMessage: String,
    @DrawableRes val imageResourceId: Int,
    val viewType: Int
    ) {
        companion object {
            const val VIEW_TYPE_LEFT = 0
            const val VIEW_TYPE_RIGHT = 1
        }
    }
