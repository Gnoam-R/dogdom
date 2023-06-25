package com.roh.dogdom.data.firebase

import com.google.firebase.database.PropertyName

data class UserInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var userId : Int,
    @get:PropertyName("아이디") @set:PropertyName("아이디") var email : Int,
    @get:PropertyName("비밀번호") @set:PropertyName("비밀번호") var password : Int,
    @get:PropertyName("닉네임") @set:PropertyName("닉네임") var nickname : Int,
    @get:PropertyName("역할") @set:PropertyName("역할") var role : Int,
)
data class PostInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var postId : Int,
    @get:PropertyName("내용") @set:PropertyName("내용") var content : Int,
    @get:PropertyName("이미지") @set:PropertyName("이미지") var image : Int,
    @get:PropertyName("작성자") @set:PropertyName("작성자") var UserId : Int,
    @get:PropertyName("작성날짜") @set:PropertyName("작성날짜") var createdAt : Int,
    @get:PropertyName("수정날짜") @set:PropertyName("수정날짜") var modifiedAt : Int,
)
data class CommentInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var commentId : Int,
    @get:PropertyName("내용") @set:PropertyName("내용") var content : Int,
    @get:PropertyName("게시글") @set:PropertyName("게시글") var postId : Int,
    @get:PropertyName("작성자") @set:PropertyName("작성자") var userId : Int,
    @get:PropertyName("작성날짜") @set:PropertyName("작성날짜") var createdAt : Int,
    @get:PropertyName("수정날짜") @set:PropertyName("수정날짜") var modifiedAt : Int,
)
data class LikeInfo (
    @get:PropertyName("고유 아이디") @set:PropertyName("고유 아이디") var likeId : Int,
    @get:PropertyName("회원") @set:PropertyName("회원") var userId : Int,
    @get:PropertyName("게시글") @set:PropertyName("게시글") var postId : Int,

    )