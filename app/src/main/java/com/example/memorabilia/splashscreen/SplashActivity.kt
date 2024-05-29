package com.example.memorabilia.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.Glide
import com.example.memorabilia.R
import com.example.memorabilia.databinding.ActivitySplashBinding
import com.example.memorabilia.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreenActivity = installSplashScreen()
        setContentView(binding.root)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)

        // Load the GIF using Glide
        Glide.with(this)
            .asGif()
            .load(R.drawable.splashlogo) // Replace with your GIF file name
            .into(binding.imageView)
    }
}
