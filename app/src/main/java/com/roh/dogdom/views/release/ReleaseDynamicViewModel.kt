package com.roh.dogdom.views.release

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roh.dogdom.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReleaseDynamicViewModel @Inject constructor() : ViewModel(){
    var viewId: ReleaseDynamicViewId = ReleaseDynamicViewId.BASE
    private val _loadImage = SingleLiveEvent<Unit>()
    val loadImage: LiveData<Unit> get() = _loadImage
    private val _btBack = SingleLiveEvent<Unit>()
    val btBack: LiveData<Unit> get() = _btBack
    private val _btNext = SingleLiveEvent<Unit>()
    val btNext: LiveData<Unit> get() = _btNext
    private val _releaseTitle = MutableLiveData("")
    val releaseTitle: LiveData<String> get() = _releaseTitle
    private val _releaseComment = MutableLiveData("")
    val releaseComment: LiveData<String> get() = _releaseComment

    fun onClick(viewType: ReleaseDynamicViewId) {
        when (viewType) {
            ReleaseDynamicViewId.BASE -> return
            ReleaseDynamicViewId.CANCEL -> _btBack.call()
            ReleaseDynamicViewId.RELEASE -> _btNext.call()
            ReleaseDynamicViewId.TITLE -> return
            ReleaseDynamicViewId.DESCRIPTION -> return
            ReleaseDynamicViewId.IMAGE -> _loadImage.call()
            ReleaseDynamicViewId.IMAGE_CLOSE -> return
            ReleaseDynamicViewId.IMAGE_RELEASE -> return
        }
    }

    fun getTitle(text: CharSequence) {
        _releaseTitle.value = text.toString()
    }
    fun getComment(text: CharSequence) {
        _releaseComment.value = text.toString()
    }
}

enum class ReleaseDynamicViewId() {
    BASE,
    CANCEL,
    RELEASE,
    TITLE,
    DESCRIPTION,
    IMAGE,
    IMAGE_CLOSE,
    IMAGE_RELEASE,
}