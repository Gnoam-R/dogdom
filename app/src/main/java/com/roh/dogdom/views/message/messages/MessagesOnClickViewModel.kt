package com.roh.dogdom.views.message.messages

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.roh.dogdom.R
import com.roh.dogdom.api.ChatGptResponse
import com.roh.dogdom.data.chatgpt.ChatGptInfo
import com.roh.dogdom.data.chatgpt.ChatGptRepository
import com.roh.dogdom.data.message.messages.MessagesRepository
import com.roh.dogdom.databinding.FragmentMessagesOnClickBinding
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MessagesOnClickViewModel @Inject constructor(
    private val chatGptRepository: ChatGptRepository
) : ViewModel(){

//    var etChattext : EditText? = null
    lateinit var chatGptInfo : ChatGptInfo
    private var myAdapter : MessageChatGptAdapter? = null
    private val _goMain = SingleLiveEvent<Unit>()
    val goMain: LiveData<Unit> get() = _goMain

    private val _sendMessage = SingleLiveEvent<Unit>()
    val sendMessage: LiveData<Unit> get() = _sendMessage

    fun goMain() {
        Log.e("MessagesOnClickViewModel", "goMain")
        _goMain.call()
    }

    fun initChatGpt(chatGptInfo: ChatGptInfo) {
        this.chatGptInfo = chatGptInfo
        chatGptRepository.initChatGpt()
    }

    fun sendChatGptMessage(binding: FragmentMessagesOnClickBinding, adapter: MessageChatGptAdapter) {
        myAdapter = adapter
        if(binding.etChat.text.toString() != ""){
            var myMessage = binding.etChat.text.toString()
            chatGptInfo.addData("roh", myMessage, R.drawable.iv_boy1 , 1)
            requestJob(myMessage)     // gpt에 코드 전달 및 수신 받음
        }
        myAdapter!!.notifyDataSetChanged()
    }

    private fun requestJob(question : String)  {
        viewModelScope.launch {
            requestChatGpt(question)     // gpt에 코드 전달 및 수신 받음
        }
    }

    private suspend fun requestChatGpt(question : String) {
        val resultDeferred = viewModelScope.async(Dispatchers.IO) {
            chatGptRepository.requestChatGpt(question)
        }
        val result = try {
            resultDeferred.await()
        } catch (e: Exception) {
            Log.e("ChatGptViewModel", "requestChatGpt: ${e.message}")
            null
        }
        resultDeferred.invokeOnCompletion {
            Log.e("ChatGptViewModel", "requestChatGpt: invokeOnCompletion")
            if (result != null) {
                Log.e("ChatGptViewModel", "requestChatGpt: check")
                var yourMessage = result.choices.get(0).message.content
                chatGptInfo.addData("yun", yourMessage, R.drawable.iv_boy1, 0)
                myAdapter!!.notifyDataSetChanged()
            }
        }
    }
    fun sendMessage() {
        _sendMessage.call()
    }

    fun setBottomNav() {
//        bottomMenuRepository.setBottomNavigation()
    }



    
}