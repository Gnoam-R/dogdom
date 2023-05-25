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
import androidx.recyclerview.widget.LinearLayoutManager
import com.roh.dogdom.R
import com.roh.dogdom.api.ChatGptResponse
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.chatgpt.ChatGptInfo
import com.roh.dogdom.databinding.FragmentMessagesOnClickBinding
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesOnClickFragment : BaseFragment<FragmentMessagesOnClickBinding>(R.layout.fragment_messages_on_click) {

    private val viewModel by viewModels<MessagesOnClickViewModel>()
    private var imm : InputMethodManager? = null
    private var gptResponse : ChatGptResponse? = null
    private var chatGptInfo = ChatGptInfo()

    lateinit var MyAdapter : MessageChatGptAdapter

//    var chatGptMyInfoList = mutableListOf(ChatGptMyInfo())
//    var chatGptYourInfoList = mutableListOf(ChatGptYourInfo())
//    private lateinit var MyAdapter : MessageChatGptAdapter

    var etChattext : EditText? = null
    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        initViewModelCallback()
        viewModel.initChatGpt()

        etChattext = binding.etChat
        binding.etChat.setSelection(binding.etChat.length())
//


//        MyAdapter = MessageChatGptAdapter(mContext, chatGptInfo.setData())

//        binding.rvChatGpt.adapter = MyAdapter

//        chatGptMyInfoList.removeAt(0)   // init
//        chatGptInfo.add(ChatGptMyInfo("roh","hello world",R.drawable.iv_boy1))

//        chatGptInfo.addData("roh", "ex", R.drawable.iv_boy1 , 0)
//        chatGptInfo.addData("roh", "easdfafddfx", R.drawable.iv_boy1 , 1)
//        chatGptInfo.addData("roh", "esfsfsx", R.drawable.iv_boy1 , 1)

        MyAdapter = MessageChatGptAdapter(mContext, chatGptInfo.getData())
        binding.rvChatGpt.adapter = MyAdapter
        binding.rvChatGpt.layoutManager = LinearLayoutManager(this.context)

        binding.sendButton.setOnClickListener {
            if(etChattext!!.text.toString() != ""){
                var myMessage = etChattext!!.text.toString()
                chatGptInfo.addData("roh", myMessage, R.drawable.iv_boy1 , 1)
                gptResponse = viewModel.requestChatGpt(myMessage)       // gpt에 전달해주는 코드
            }
            if(gptResponse != null) {
//                Log.e("gptResponse", gptResponse!!.choices.get(0).message.content)
                var yourMessage = gptResponse!!.choices.get(0).message.content
                chatGptInfo.addData("yun", yourMessage, R.drawable.iv_boy1 , 0)
//                chatGptYourInfoList.add(ChatGptYourInfo("JY", yourMessage, R.drawable.iv_article))
//                binding.tvYourMessage.text = gptResponse!!.choices.get(0).message.content
            }
            MyAdapter.notifyDataSetChanged()
        }
    }

    private fun initViewModelCallback() {
        with(viewModel) {
//            goMain.observe(viewLifecycleOwner, Observer {
//                val direction: NavDirections = com.roh.dogdom.views.login.LoginFragmentDirections.actionLoginFragmentToMasterMainFragment()
//                findNavController().navigate(direction)
//            })
//
        }
    }

}