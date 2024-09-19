package com.roh.dogdom.views.home

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.data.home.MainPost2
import com.roh.dogdom.databinding.FragmentHomeSecondContentsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeContentsSecondFragment :
    BaseFragment<FragmentHomeSecondContentsBinding>(R.layout.fragment_home_second_contents){

    val datas = mutableListOf<Int>()

    private var ItemList = MainPost()
    private var ItemList2 = MainPost2()
    private lateinit var MyAdapter : HomeAdapter


    override fun init() {
        MyAdapter = HomeAdapter(ItemList2)
        binding.mainVtRvContainer.adapter = MyAdapter
        binding.mainVtRvContainer.layoutManager = LinearLayoutManager(this.context)

        MyAdapter.setOnItemClickListener(object : HomeAdapter.OnItemClickListener{
            override fun onItemClick(pos: Int) {
                Log.e("HomeContentsSecondFragment", "onItemClick: $pos" )
            }
        })
    }

    private fun moveNextScreen() {
//        findNavController().navigate(
//            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        )

    }
}