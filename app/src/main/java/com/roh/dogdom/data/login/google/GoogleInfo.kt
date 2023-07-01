package com.roh.dogdom.data.login.google

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class GoogleInfo (
    @get:PropertyName("ID") @set:PropertyName("ID") var userId : String = "",
    @get:PropertyName("전체이름") @set:PropertyName("전체이름") var name : String = "",
    @get:PropertyName("이메일") @set:PropertyName("이메일") var email : String = "",
    @get:PropertyName("프로필 사진의 주소") @set:PropertyName("프로필 사진의 주소") var profileAddress : String = "",
    @get:PropertyName("역할") @set:PropertyName("역할") var role : UserRole = UserRole.NORMAL,
)

enum class UserRole {
    @SerializedName("관리자") ADMIN,
    @SerializedName("일반") NORMAL,
    @SerializedName("탈퇴") WITHDRAWAL
}