package com.roh.dogdom.data.firebase

import com.google.gson.annotations.SerializedName

data class ImageInfo (
    @SerializedName("ID") var id : Int,
    @SerializedName("USER_NAME")var userName : String,
    @SerializedName("") var image : Int,

    )

//data class LoginInfo (
//    @SerializedName var serializedName : Int,
//    @SerializedName("USER_NAME")var userName : String,
//        )