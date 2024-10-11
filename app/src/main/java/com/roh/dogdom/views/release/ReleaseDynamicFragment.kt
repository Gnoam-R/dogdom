package com.roh.dogdom.views.release

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentReleaseDynamicBinding
import com.roh.dogdom.views.login.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleaseDynamicFragment : BaseFragment<FragmentReleaseDynamicBinding>(R.layout.fragment_release_dynamic){
    private val viewModel by viewModels<ReleaseDynamicViewModel>()
    override fun init() {
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            btCancel.observe(viewLifecycleOwner, Observer {
                Log.e("ReleaseDynamicFragment", "initViewModelCallback")
            })
        }
    }
    private fun moveNextView() {
        val direction: NavDirections = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(direction)
    }
}