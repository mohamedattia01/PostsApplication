package com.example.postsapplication.features.splash.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.postsapplication.R
import com.example.postsapplication.core.base.screens.BaseActivity
import com.example.postsapplication.core.constans.AppConstants.Companion.SPLASH_DURATION
import com.example.postsapplication.core.extensions.loadImage
import com.example.postsapplication.databinding.ActivitySplashBinding
import com.example.postsapplication.features.home.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    private lateinit var _binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = binding(R.layout.activity_splash)
        _binding.splashLogo.loadImage(gifRaw = R.raw.post, isCircularImage = true)
        loadHomeScreen()
    }

    private fun loadHomeScreen() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(SPLASH_DURATION)
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}