package com.roh.dogdom.api.dog
import retrofit2.Call
import retrofit2.http.*

interface DogRetrofitService {
    @POST("breeds/image/random")
    fun getImageCompletion(): Call<DogImageRespond>
}

