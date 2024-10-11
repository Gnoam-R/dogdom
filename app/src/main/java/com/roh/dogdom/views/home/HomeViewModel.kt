package com.roh.dogdom.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.roh.dogdom.data.bottommenu.BottomNavigationRepository
import com.roh.dogdom.data.home.HomeRepository
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val BottomNavigationRepository: BottomNavigationRepository
) : ViewModel() {
    private val _goEx = SingleLiveEvent<Unit>()
    val goEx: LiveData<Unit> get() = _goEx


    fun goEx() {
        _goEx.call()
    }
}