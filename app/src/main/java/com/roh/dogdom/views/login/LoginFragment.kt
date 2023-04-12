package com.roh.dogdom.views.login

import android.os.Bundle
import android.view.View
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login){

    private val viewModel: LocalPageViewModel by viewModel()

    override fun init() {
        binding.vm = viewModel
//        processIntent()
//        initRuleText()
//        initViewModelCallback()
        putData()
        readData()
    }



    private fun putData() {

    }
    private fun readData() {

    }
}