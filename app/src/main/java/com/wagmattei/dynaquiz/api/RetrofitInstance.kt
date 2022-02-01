package com.wagmattei.dynaquiz.api

import com.wagmattei.dynaquiz.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }

}