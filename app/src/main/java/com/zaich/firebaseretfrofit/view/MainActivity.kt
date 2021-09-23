package com.zaich.firebaseretfrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.zaich.firebaseretfrofit.databinding.ActivityMainBinding
import com.zaich.firebaseretfrofit.model.NotifModel
import com.zaich.firebaseretfrofit.model.PushNotification


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()
    private val topic = "/topics/all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener{
            val title = binding.etJudul.text.toString()
            val body  = binding.etDesc.text.toString()

            if (title.isNotEmpty() || body.isNotEmpty()){
                PushNotification(
                    NotifModel(body,title),
                    topic
                ).also {
                    mainViewModel.setNotif(it)
                }
                Toast.makeText(this, "sended", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "tidak ada data", Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.getNotif().observe(this,{
            Log.d("mainActivity","onCreate : $it")
            if (it!=null){
                Toast.makeText(this, "mantapp", Toast.LENGTH_SHORT).show()
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.pbSearch.visibility = View.VISIBLE
        } else {
            binding.pbSearch.visibility = View.GONE
        }
    }

    companion object {
        const val TAG = "USER"
    }

}