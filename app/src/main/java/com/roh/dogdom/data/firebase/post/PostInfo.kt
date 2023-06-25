package com.roh.dogdom.data.firebase.post

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class ImageInfo (
    @SerializedName("ID") var id : Int,
    @SerializedName("USER_NAME")var userName : String,
    @SerializedName("") var image : Int,
    )
data class PostInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var postId : Int,
    @get:PropertyName("내용") @set:PropertyName("내용") var content : Int,
    @get:PropertyName("이미지") @set:PropertyName("이미지") var image : Int,
    @get:PropertyName("작성자") @set:PropertyName("작성자") var UserId : Int,
    @get:PropertyName("작성날짜") @set:PropertyName("작성날짜") var createdAt : Int,
    @get:PropertyName("수정날짜") @set:PropertyName("수정날짜") var modifiedAt : Int,
)