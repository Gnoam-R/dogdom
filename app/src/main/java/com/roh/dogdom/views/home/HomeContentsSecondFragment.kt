package com.roh.dogdom.views.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.main.MainPost
import com.roh.dogdom.databinding.FragmentMasterMainSecondContentsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeContentsSecondFragment :
    BaseFragment<FragmentMasterMainSecondContentsBinding>(R.layout.fragment_master_main_second_contents){

    private var ItemList = MainPost()
    private lateinit var MyAdapter : HomeAdapter

    override fun init() {
        MyAdapter = HomeAdapter(ItemList)
        binding.mainVtRvContainer.adapter = MyAdapter
        binding.mainVtRvContainer.layoutManager = LinearLayoutManager(this.context)
    }

    private fun moveNextScreen() {
//        findNavController().navigate(
//            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        )
    }
}