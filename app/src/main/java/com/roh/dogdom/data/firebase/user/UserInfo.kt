package com.roh.dogdom.data.firebase.user

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class UserInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var userId : Int,
    @get:PropertyName("아이디") @set:PropertyName("아이디") var email : Int,
    @get:PropertyName("비밀번호") @set:PropertyName("비밀번호") var password : Int,
    @get:PropertyName("닉네임") @set:PropertyName("닉네임") var nickname : Int,
    @get:PropertyName("역할") @set:PropertyName("역할") var role : Int,
)