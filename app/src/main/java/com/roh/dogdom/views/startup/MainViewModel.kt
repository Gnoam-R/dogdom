package com.roh.dogdom.views.startup

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.roh.dogdom.data.bottommenu.BottomMenuRepository
import com.roh.dogdom.data.main.MasterMainRepository
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bottomMenuRepository: BottomMenuRepository
) : ViewModel() {
    private val _goEx = SingleLiveEvent<Unit>()
    val goEx: LiveData<Unit> get() = _goEx

    init {
    }

    fun goEx() {
        _goEx.call()
    }

    fun initBottomMenu(view : View, navController: NavController) {
        bottomMenuRepository.initBottomNavigation(view, navController)
    }

}