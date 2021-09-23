package com.zaich.firebaseretfrofit.server

import retrofit2.http.Headers
import com.zaich.firebaseretfrofit.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.zaich.firebaseretfrofit.BuildConfig.SERVER_KEY

interface ServiceInterface {
    @POST("fcm/send")
    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:application/json")
    suspend fun addNotif(@Body notification: PushNotification
    ): Response<ResponseBody>
}