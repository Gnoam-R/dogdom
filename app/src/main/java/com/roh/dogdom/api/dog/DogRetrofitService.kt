package com.roh.dogdom.api.dog

import retrofit2.Call
import retrofit2.http.*



interface DogRetrofitService {
//    @Headers(
//        "Content-Type: application/json",
//        "Authorization: Bearer $godskey"
//    )
    @POST("breeds/image/random")
    fun getImageCompletion(): Call<DogImageRespond>
}

