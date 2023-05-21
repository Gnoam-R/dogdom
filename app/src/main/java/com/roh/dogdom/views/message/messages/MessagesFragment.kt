package com.roh.dogdom.views.message.messages

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentMessagesBinding
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.startup.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesFragment : BaseFragment<FragmentMessagesBinding>(R.layout.fragment_messages) {

    private val viewModel by viewModels<MessagesViewModel>()

    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarWhite)
        mActivity = activity as MainActivity
        binding.vm = viewModel
        var navController: NavController = findNavController()
        Log.e("MessagesFragment","${navController.currentDestination?.id}")

        binding.appCompatImageButton.setOnClickListener {

        }
//        binding.rvMessagesUser.adapter = MessagesAdapter()
//        binding.rvMessagesMessage.adapter = MessagesAdpater()
    }
}