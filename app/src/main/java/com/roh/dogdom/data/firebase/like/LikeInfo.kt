package com.roh.dogdom.data.firebase.like

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class LikeInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var likeId : Long = 0,
    @get:PropertyName("회원") @set:PropertyName("회원") var userId : Long = 0,
    @get:PropertyName("게시글") @set:PropertyName("게시글") var postId : Long = 0,
)