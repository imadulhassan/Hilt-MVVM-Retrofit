package com.hiltMvvmRetrofit.ui.activities.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hiltMvvmRetrofit.MainActivity
import com.hiltMvvmRetrofit.databinding.ActivitySplashBinding


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
@AndroidEntryPoint
class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
         runBlocking{
          launch {
                delay(4000)
                nextActivity()
          }
       }
    }

    fun nextActivity(){
        startActivity( Intent(this, MainActivity::class.java))
        finish()
    }

}