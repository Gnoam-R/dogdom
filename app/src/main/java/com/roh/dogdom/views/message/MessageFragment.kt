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
        mActivity = activity as MainActivity
        binding.vm = viewModel
        var navController: NavController = findNavController()

        Log.e("MessageFragment","${findNavController().currentDestination?.id}")

        msgHzAdapter = MessageHorizontalAdapter(msgHzPost)
        msgVtAdapter = MessageVerticalAdapter(msgVtPost)

        binding.rvMessageHorizontal.adapter = msgHzAdapter
        binding.rvMessageVertical.adapter = msgVtAdapter
        binding.rvMessageHorizontal.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMessageVertical.layoutManager = LinearLayoutManager(mContext)

        msgHzAdapter.setOnItemClickListener(object : MessageHorizontalAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {
                Log.e("msgHzAdapter", "pos : $pos")
                when(pos) {
                    0 -> {
                    }
                    1 -> {
                        val direction = MessageFragmentDirections.actionMessageFragmentToMessagesOnClickFragment()
                        findNavController().navigate(direction)
                    }


                }
            }
        })
        msgVtAdapter.setOnItemClickListener(object : MessageVerticalAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {
                Log.e("msgVtAdapter", "pos : $pos")
            }
        })
    }
}