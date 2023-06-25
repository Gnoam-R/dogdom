package com.roh.dogdom.data.firebase.user

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class UserInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var userId : Long = 0,
    @get:PropertyName("아이디") @set:PropertyName("아이디") var email : String = "",
    @get:PropertyName("비밀번호") @set:PropertyName("비밀번호") var password : String = "",
    @get:PropertyName("닉네임") @set:PropertyName("닉네임") var nickname : String = "",
    @get:PropertyName("역할") @set:PropertyName("역할") var role : UserRole = UserRole.ADMIN,
)

enum class UserRole {
    @SerializedName("관리자") ADMIN,
    @SerializedName("일반") NORMAL,
    @SerializedName("탈퇴") WITHDRAWAL
}