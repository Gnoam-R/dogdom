package com.roh.dogdom.views.messages

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.roh.dogdom.data.bottommenu.BottomMenuRepository
import com.roh.dogdom.data.login.LoginRepository
import com.roh.dogdom.data.messages.MessagesRepository
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository
) : ViewModel(){

    private val _goMain = SingleLiveEvent<Unit>()
    val goMain: LiveData<Unit> get() = _goMain

    private val _goEmailSignUp = SingleLiveEvent<Unit>()
    val goEmailSignUp: LiveData<Unit> get() = _goEmailSignUp

    fun goMain() {
        _goMain.call()
    }
    fun goEmailSignUp() {
    }
    fun setBottomNav() {
        Log.e("LoginViewModel", "setBottomNav")
//        bottomMenuRepository.setBottomNavigation()
    }

}