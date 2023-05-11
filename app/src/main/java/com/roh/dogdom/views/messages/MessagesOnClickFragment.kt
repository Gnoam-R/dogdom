package com.roh.dogdom.views.messages

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentMessagesBinding
import com.roh.dogdom.databinding.FragmentMessagesOnClickBinding
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.startup.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesOnClickFragment : BaseFragment<FragmentMessagesOnClickBinding>(R.layout.fragment_messages_on_click) {

    private val viewModel by viewModels<MessagesViewModel>()

    override fun init() {

    }
}