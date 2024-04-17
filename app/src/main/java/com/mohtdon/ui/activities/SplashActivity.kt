package com.mohtdon.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohtdon.mohtdon.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var mySound: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        this.window.statusBarColor = resources.getColor(R.color.white, this.theme)
//        mySound = MediaPlayer.create(this, R.raw.splash_sound)
//        mySound?.start()
//
        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            nextActivity()
        }
//    }
    }

    private fun nextActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mySound?.release()
    }
}
