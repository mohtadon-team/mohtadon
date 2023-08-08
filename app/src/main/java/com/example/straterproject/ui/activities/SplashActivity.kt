package com.example.straterproject.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.data.dataSource.remote.service.PrayerTimesService
import com.example.straterproject.R
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()


//lifecycleScope.launch {
//    Retrofit.Builder().baseUrl("http://api.aladhan.com")
//        .client(OkHttpClient.Builder().build())
//        .addConverterFactory(GsonConverterFactory.create()).build()
//        .create(PrayerTimesService::class.java).getTodayPrayerTimes()
//        .body()?.data?.timings?.let {
//            Log.i(
//                "ahmed", it.Asr
//            )
//        }
//}

//        Handler().postDelayed({
//            val intent= Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        },3000)
    }
}