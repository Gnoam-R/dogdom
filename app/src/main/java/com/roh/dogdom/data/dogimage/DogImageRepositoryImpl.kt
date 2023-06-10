package com.roh.dogdom.data.dogimage

import android.util.Log
import com.roh.dogdom.api.dog.DogImageRespond
import com.roh.dogdom.api.dog.DogRetrofitClient
import com.roh.dogdom.api.dog.DogRetrofitService
import retrofit2.Callback
import retrofit2.Retrofit

class DogImageRepositoryImpl : DogImageRepository{

    // retrofit2 test
    private lateinit var retrofitService: DogRetrofitService
    private lateinit var retrofit : Retrofit

    override fun requestDogAPi() {
        retrofitService.getImageCompletion().enqueue(object : Callback<DogImageRespond> {
            override fun onResponse(
                call: retrofit2.Call<DogImageRespond>,
                response: retrofit2.Response<DogImageRespond>
            ) {
                if(response.isSuccessful) {
//                    mResponse = response
////                    result = response.body()!!
//                    val message = mResponse.body()?.choices?.get(0)?.message?.content
                    Log.e("requestDogAPi", "response : ${response}")
//                    callback?.onResponseDoneChatGpt(mResponse.body())
                }
            }
            override fun onFailure(call: retrofit2.Call<DogImageRespond>, t: Throwable) {
                Log.e("requestDogAPi", "t : $t")
//                callback?.onResponseFailChatGpt(t)
            }
        })
    }

//    override fun responseChatGpt() : ChatGptResponse {
//        val result = mResponse.body()!!
//        return result
//    }

    override fun initDogApi() {
        retrofit = DogRetrofitClient.getInstance()
        retrofitService = retrofit.create(DogRetrofitService::class.java)
    }
}