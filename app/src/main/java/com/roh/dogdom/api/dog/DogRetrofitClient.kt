package com.roh.dogdom.api.dog

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogRetrofitClient {
    private var instance: Retrofit? = null
    private var gson = GsonBuilder().setLenient().create()
    // 서버 주소
    private const val BASE_URL = "https://dog.ceo/dog-api/"

    // SingleTon
    fun getInstance() : Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return instance!!
    }
}