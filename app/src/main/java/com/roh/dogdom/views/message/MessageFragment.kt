package com.roh.dogdom.views.message

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.data.message.MessageHorizontalPost
import com.roh.dogdom.data.message.MessageVerticalPost
import com.roh.dogdom.databinding.FragmentMessageBinding
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.home.HomeAdapter
import com.roh.dogdom.views.startup.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : BaseFragment<FragmentMessageBinding>(R.layout.fragment_message) {

    private val viewModel by viewModels<MessageViewModel>()


    private lateinit var msgHzAdapter: MessageHorizontalAdapter
    private lateinit var msgVtAdapter: MessageVerticalAdapter
    private var msgHzPost = MessageHorizontalPost()
    private var msgVtPost = MessageVerticalPost()

    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarWhite)
        mActivity = activity as MainActivity
        binding.vm = viewModel
        var navController: NavController = findNavController()

        msgHzAdapter = MessageHorizontalAdapter(msgHzPost)
        msgVtAdapter = MessageVerticalAdapter(msgVtPost)

        binding.rvMessageHorizontal.adapter = msgHzAdapter
        binding.rvMessageVertical.adapter = msgVtAdapter
        binding.rvMessageHorizontal.layoutManager = LinearLayoutManager(mContext)
        binding.rvMessageVertical.layoutManager = LinearLayoutManager(mContext)
    }
}