package com.roh.dogdom.views.release

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentReleaseDynamicBinding
import com.roh.dogdom.navigator.AppNavigator
import com.roh.dogdom.util.MoveViewType
import com.roh.dogdom.views.login.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReleaseDynamicFragment : BaseFragment<FragmentReleaseDynamicBinding>(R.layout.fragment_release_dynamic){
    @Inject lateinit var navigator: AppNavigator
    private val viewModel by viewModels<ReleaseDynamicViewModel>()
    override fun init() {
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            btBack.observe(viewLifecycleOwner, Observer {
                moveView(MoveViewType.BACK)
            })
            btNext.observe(viewLifecycleOwner, Observer {
                moveView(MoveViewType.NEXT)
            })
        }
    }
    private fun moveView(type: MoveViewType) {
        val direction: NavDirections = when (type) {
            MoveViewType.BACK -> ReleaseDynamicFragmentDirections.actionReleaseDynamicFragmentToHomeFragment()
            MoveViewType.NEXT -> ReleaseDynamicFragmentDirections.actionReleaseDynamicFragmentToHomeFragment()
            else -> throw IllegalArgumentException("Invalid MoveViewType")
        }
        findNavController().navigate(direction)
    }
}