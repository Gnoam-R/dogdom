package com.roh.dogdom.views.message.messages

import android.content.Context
import android.graphics.Rect
import android.os.Message
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat.getRootWindowInsets
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.roh.dogdom.R
import com.roh.dogdom.api.ChatGptResponse
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.chatgpt.ChatGptInfo
import com.roh.dogdom.databinding.FragmentMessagesOnClickBinding
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.home.HomeAdapter
import com.roh.dogdom.views.login.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesOnClickFragment : BaseFragment<FragmentMessagesOnClickBinding>(R.layout.fragment_messages_on_click) {

    private val viewModel by viewModels<MessagesOnClickViewModel>()
    lateinit var myAdapter : MessageChatGptAdapter

    private var chatGptInfo = ChatGptInfo()
    private var gptResponse : ChatGptResponse? = null


    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        binding.vm = viewModel
        initViewModelCallback()
        viewModel.initChatGpt(chatGptInfo)
        initRecycler(binding, mContext)
    }

    fun initRecycler(binding: FragmentMessagesOnClickBinding, mContext: Context) {
//        mbinding =  binding
        myAdapter = MessageChatGptAdapter(mContext, chatGptInfo.getData())
        binding.rvChatGpt.adapter = myAdapter
        binding.rvChatGpt.layoutManager = LinearLayoutManager(mContext)
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goMain.observe(viewLifecycleOwner, Observer {
                // 바텀 네비게이션 설정
            })
            sendMessage.observe(viewLifecycleOwner, Observer {
                // 메세지 전송
                sendChatGptMessage(binding, myAdapter)
            })
        }
    }

}