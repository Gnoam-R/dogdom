package com.roh.dogdom.views.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.roh.dogdom.data.main.MasterMainRepository
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MasterMainViewModel @Inject constructor(
    private val repository: MasterMainRepository
) : ViewModel() {
    private val _goEx = SingleLiveEvent<Unit>()
    val goEx: LiveData<Unit> get() = _goEx

    fun goEx() {
        _goEx.call()
    }
}