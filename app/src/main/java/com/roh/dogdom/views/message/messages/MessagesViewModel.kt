package com.roh.dogdom.views.message.messages

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.roh.dogdom.data.message.messages.MessagesRepository
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
//        BottomNavigationRepository.setBottomNavigation()
    }

    
}