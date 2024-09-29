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

    private val _btCancle = SingleLiveEvent<Unit>()
    val btCancel: LiveData<Unit> get() = _btCancle

    private val _releaseTitle = MutableLiveData("")
    val releaseTitle: LiveData<String> get() = _releaseTitle

    private val _releaseDescription = MutableLiveData("")
    val releaseDescription: LiveData<String> get() = _releaseDescription

    fun loadImage(num: Int) {

    }

    fun onClickedCancel() {
        _btCancle.call()
    }

    fun onClickedRelease() {

    }

    fun releaseTitle(text: CharSequence) {
        Log.e("releaseDescription", "$text")
        _releaseTitle.value = text.toString()
    }
    fun releaseDescription(text: CharSequence) {
        Log.e("releaseDescription", "$text")
        _releaseDescription.value = text.toString()
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {

    }
}