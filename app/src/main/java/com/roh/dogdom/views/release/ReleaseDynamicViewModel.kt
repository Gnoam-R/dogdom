package com.roh.dogdom.views.release

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.roh.dogdom.R
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReleaseDynamicViewModel @Inject constructor(

) : ViewModel(){
    private val _loadImage = SingleLiveEvent<Unit>()
    val loadImage: LiveData<Unit> get() = _loadImage

    private val _btBack = SingleLiveEvent<Unit>()
    val btBack: LiveData<Unit> get() = _btBack

    private val _btNext = SingleLiveEvent<Unit>()
    val btNext: LiveData<Unit> get() = _btNext

    private val _releaseTitle = MutableLiveData("")
    val releaseTitle: LiveData<String> get() = _releaseTitle

    private val _releaseDescription = MutableLiveData("")
    val releaseDescription: LiveData<String> get() = _releaseDescription

    fun loadImage(num: Int) {

    }

    fun onClickedBack() {
        _btBack.call()
    }

    fun onClickedNext() {
        _btNext.call()
    }

    fun releaseTitle(text: CharSequence) {
        Log.e("releaseDescription", "$text")
        _releaseTitle.value = text.toString()
    }
    fun releaseDescription(text: CharSequence) {
        Log.e("releaseDescription", "$text")
        _releaseDescription.value = text.toString()
    }
}