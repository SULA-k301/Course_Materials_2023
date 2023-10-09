package com.zeek1910.examples.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.zeek1910.examples.R
import com.zeek1910.examples.data.AppSettings

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var buttonSkip: TextView
    private lateinit var buttonNext: TextView
    private lateinit var buttonGetStarted: Button
    private lateinit var dotsIndicator: WormDotsIndicator

    private lateinit var appSettings: AppSettings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        appSettings = AppSettings(this)

        viewPager = findViewById(R.id.viewPager)
        buttonSkip = findViewById(R.id.buttonSkip)
        buttonNext = findViewById(R.id.buttonNext)
        buttonGetStarted = findViewById(R.id.buttonGetStarted)
        dotsIndicator = findViewById(R.id.dotsIndicator)

        viewPager.adapter = OnboardingAdapter()

        buttonSkip.setOnClickListener { markOnboardingAsCompleted() }
        buttonNext.setOnClickListener { viewPager.currentItem++ }
        buttonGetStarted.setOnClickListener { markOnboardingAsCompleted() }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                buttonGetStarted.isVisible = position + 1 == PAGE_COUNT
            }
        })
        dotsIndicator.attachTo(viewPager)
    }

    private fun markOnboardingAsCompleted(){
        appSettings.isFirstLaunch = false
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    companion object{
        private const val PAGE_COUNT = 4
    }
}