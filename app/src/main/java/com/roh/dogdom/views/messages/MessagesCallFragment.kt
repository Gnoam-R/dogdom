package com.roh.dogdom.views.messages

import androidx.fragment.app.viewModels
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentMessagesCallBinding
import com.roh.dogdom.databinding.FragmentMessagesOnClickBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesCallFragment : BaseFragment<FragmentMessagesCallBinding>(R.layout.fragment_messages_call) {

    private val viewModel by viewModels<MessagesViewModel>()

    override fun init() {

    }
}