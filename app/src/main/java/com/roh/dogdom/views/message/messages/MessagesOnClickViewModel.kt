package com.roh.dogdom.views.message.messages

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roh.dogdom.R
import com.roh.dogdom.api.chatGpt.ChatGptResponse
import com.roh.dogdom.data.chatgpt.ChatGptInfo
import com.roh.dogdom.data.chatgpt.ChatGptRepository
import com.roh.dogdom.data.chatgpt.ResponseChatGptListener
import com.roh.dogdom.databinding.FragmentMessagesOnClickBinding
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MessagesOnClickViewModel @Inject constructor(
    private val chatGptRepository: ChatGptRepository
) : ViewModel() , ResponseChatGptListener {

//    var etChattext : EditText? = null
    lateinit var chatGptInfo : ChatGptInfo
    private var myAdapter : MessageChatGptAdapter? = null
    private val _goMain = SingleLiveEvent<Unit>()
    val goMain: LiveData<Unit> get() = _goMain

    private val _sendMessage = SingleLiveEvent<Unit>()
    val sendMessage: LiveData<Unit> get() = _sendMessage

    fun sendMessage() {
        _sendMessage.call()
    }

    fun goMain() {
        Log.e("MessagesOnClickViewModel", "goMain")
        _goMain.call()
    }

    fun initChatGpt(chatGptInfo: ChatGptInfo) {
        this.chatGptInfo = chatGptInfo
        chatGptRepository.initChatGpt()
        chatGptRepository.setCompletionCallback(this)
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

    private fun responseChatGpt(chatGptResponse: ChatGptResponse?)  {
        chatGptInfo.addData("yun", chatGptResponse?.choices?.get(0)?.message?.content!!, R.drawable.iv_boy1 , 0)
        myAdapter!!.notifyDataSetChanged()
    }

    private  fun requestChatGpt(question : String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                chatGptRepository.requestChatGpt(question)
            }catch (e: Exception) {
                Log.e("ChatGptViewModel", "requestChatGpt: ${e.message}")
            }
        }
    }

    // callback
    override fun onResponseDoneChatGpt(chatGptResponse: ChatGptResponse?) {
        Log.e("onResponseDoneChatGpt", ":" + chatGptResponse)
        responseChatGpt(chatGptResponse)
    }

    override fun onResponseFailChatGpt(t: Throwable) {
        Log.e("onResponseFailChatGpt", ":" + t)
    }

    fun setBottomNav() {
//        bottomMenuRepository.setBottomNavigation()
    }

    
}