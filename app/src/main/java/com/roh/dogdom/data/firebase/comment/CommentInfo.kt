package com.roh.dogdom.data.firebase.comment

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class CommentInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var commentId : Int,
    @get:PropertyName("내용") @set:PropertyName("내용") var content : Int,
    @get:PropertyName("게시글") @set:PropertyName("게시글") var postId : Int,
    @get:PropertyName("작성자") @set:PropertyName("작성자") var userId : Int,
    @get:PropertyName("작성날짜") @set:PropertyName("작성날짜") var createdAt : Int,
    @get:PropertyName("수정날짜") @set:PropertyName("수정날짜") var modifiedAt : Int,
)