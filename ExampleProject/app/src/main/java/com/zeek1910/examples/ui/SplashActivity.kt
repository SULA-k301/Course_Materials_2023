package com.zeek1910.examples.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.zeek1910.examples.R
import com.zeek1910.examples.data.AppSettings

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var appSettings: AppSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        appSettings = AppSettings(this)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = when {
                appSettings.isFirstLaunch -> Intent(this, OnboardingActivity::class.java)
                appSettings.isUserLogin() -> Intent(this, MainActivity::class.java)
                else -> Intent(this, SignInActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 1000)
    }
}