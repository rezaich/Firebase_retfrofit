package com.zaich.firebaseretfrofit.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaich.firebaseretfrofit.model.PushNotification
import com.zaich.firebaseretfrofit.server.ServerClient
import com.zaich.firebaseretfrofit.server.ServiceInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val serverInterface: ServiceInterface =
        ServerClient().getApiClient()!!.create(ServiceInterface::class.java)
    private val notif = MutableLiveData<PushNotification>()

    fun setNotif(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch{
        try {
            val response = serverInterface.addNotif(notification)
            if (response.isSuccessful){
                notif.postValue(notification)
                Log.d(TAG, "success connect server")
            } else {
                Log.e(TAG, response.errorBody().toString())
            }
        } catch (e: Exception){
            Log.e(TAG, e.toString())
        }
    }
    fun getNotif() : MutableLiveData<PushNotification> = notif
}