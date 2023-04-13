package com.roh.dogdom.views.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.todo.Todo
import com.roh.dogdom.databinding.FragmentLoginBinding
import com.roh.dogdom.views.splash.SplashFragmentDirections
import com.roh.dogdom.views.todo.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login){

//    private val viewModel: LocalPageViewModel by viewModel()

    private val userViewModel:TodoListViewModel by viewModels()

    override fun init() {
        moveNextScreen()

//        binding.vm = userViewModel
//        putData()
//        readData()

    }

    private fun putData() {
        val getName = "노형우"
        val getAge = "28"
        val todo= Todo(getName,getAge.toInt())
        todo.id  = 1
        userViewModel.insertTodo(todo)
        Log.e("LoginFragmnet", "check userViewModel1 : ${todo}")
    }
    private fun readData() {

        lifecycleScope.launch {
//            userViewModel.getTodos().collect { todos ->
//                // todo: 가져온 데이터 처리
//                Log.e("LoginFragmnet", "check userViewModel : $todos")
//            }
//
//            userViewModel.getTodos().collect { todos ->
//                // todo: 가져온 데이터 처리
//                Log.e("LoginFragmnet", "check userViewModel : $todos")
//            }
        }
    }

    private fun moveNextScreen() {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        )
    }
}