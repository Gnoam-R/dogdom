package com.roh.dogdom.data.chatgpt

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//data class ChatGptMyInfo (
//    var MyName: String = "",
//    var MyMessage: String = "",
//    var MyProfileImage: Int = 0,
//        )
//
//data class ChatGptYourInfo (
//    var YourName: String = "",
//    var YourMessage: String = "",
//    var YourProfileImage: Int = 0,
//    )

class ChatGptInfo {
    companion object {
        val dataList: MutableList<ChatGptDataSource> = mutableListOf()
    }

//    companion object {
//        var mName : String? = null
//        var mMessage : String? = null
//        var mImage : Int? = null
//        var mViewType : Int? = null
//    }

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
