package com.roh.dogdom.views.main

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.main.MainPost
import com.roh.dogdom.databinding.FragmentMasterMainSecondContentsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MasterMainContentsSecondFragment :
    BaseFragment<FragmentMasterMainSecondContentsBinding>(R.layout.fragment_master_main_second_contents){

    private var ItemList = MainPost()
    private lateinit var MyAdapter : MasterMainAdapter

    override fun init() {
        MyAdapter = MasterMainAdapter(ItemList)
        binding.mainVtRvContainer.adapter = MyAdapter
        binding.mainVtRvContainer.layoutManager = LinearLayoutManager(this.context)
    }

    private fun moveNextScreen() {
//        findNavController().navigate(
//            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        )
    }
}