package com.zaich.firebaseretfrofit.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.zaich.firebaseretfrofit.BuildConfig.BASE_URL

class ServerClient {
    var retrofit:Retrofit? = null

    fun getApiClient():Retrofit? {
        if(retrofit == null ){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}