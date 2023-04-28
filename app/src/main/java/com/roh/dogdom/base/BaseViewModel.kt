package com.roh.dogdom.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job


abstract class BaseViewModel : ViewModel() {
    abstract class BaseViewModel : ViewModel() {

        // RxJava
        // protected val compositeDisposable = CompositeDisposable()

        // Coroutine
        private var job: Job? = null

//        private var _backClick = SingleLiveEvent<Unit>()
//        val backClick: LiveData<Unit> get() = _backClick
//        private val _isLoading = MutableLiveData<Boolean>(false)
//        val isLoading: LiveData<Boolean> get() = _isLoading
//        private val _isLottieLoading = MutableLiveData<Boolean>(false)
//        val isLottieLoading: LiveData<Boolean> get() = _isLottieLoading

        override fun onCleared() {
            job?.cancel()
            super.onCleared()
        }

//        fun showProgress() {
//            _isLoading.value = true
//        }
//
//        fun hideProgress() {
//            _isLoading.value = false
//        }
//
//        fun showLottieProgress() {
//            _isLottieLoading.value = true
//        }
//
//        fun hideLottieProgress() {
//            _isLottieLoading.value = false
//        }
//
//        fun onBackClick() {
//            _backClick.call()
//        }
    }
}