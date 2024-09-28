package com.roh.dogdom.views.release

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
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

    fun loadImage(num: Int) {

    }

    fun onClickedCancel() {
        Log.e("ReleaseDynamicViewModel", "onClickedCancel")
        _btCancle.call()
    }

    fun onClickedRelease() {

    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {

    }
}