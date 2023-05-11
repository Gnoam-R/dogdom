package com.roh.dogdom.views.personal

import android.Manifest
import android.app.NotificationManager
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.views.startup.MainActivity
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.permission.PermissionRepository
import com.roh.dogdom.data.permission.PermissionRepositoryImpl
import com.roh.dogdom.databinding.FragmentLoginBinding
import com.roh.dogdom.databinding.FragmentPersonalBinding
import com.roh.dogdom.util.enumUiColorPos
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonalFragment : BaseFragment<FragmentPersonalBinding>(R.layout.fragment_personal){
//    private val viewModel: LocalPageViewModel by viewModel()
//    private val viewModel by viewModels<LoginViewModel>()

    private val repository: PermissionRepository = PermissionRepositoryImpl()

    override fun init() {

    }
}