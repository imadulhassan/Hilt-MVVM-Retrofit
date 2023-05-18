package com.sample.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.sample.databinding.ActivitySplashBinding
import com.sample.ui.home.HomeActivity


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
          CoroutineScope(Dispatchers.Main).launch {
                delay(4000)
                nextActivity()
          }

    }

    fun nextActivity(){
        startActivity( Intent(this, HomeActivity::class.java))
        finish()
    }

}