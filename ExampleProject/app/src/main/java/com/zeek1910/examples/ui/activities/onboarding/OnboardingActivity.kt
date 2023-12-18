package com.zeek1910.examples.ui.activities.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.zeek1910.examples.R
import com.zeek1910.examples.data.AppSettings
import com.zeek1910.examples.App
import com.zeek1910.examples.ui.activities.signin.SignInActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var buttonSkip: TextView
    private lateinit var buttonNext: TextView
    private lateinit var buttonGetStarted: Button
    private lateinit var dotsIndicator: WormDotsIndicator

    private val viewModel: OnboardingViewModel by viewModels { OnboardingViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        buttonSkip = findViewById(R.id.buttonSkip)
        buttonNext = findViewById(R.id.buttonNext)
        buttonGetStarted = findViewById(R.id.buttonGetStarted)
        dotsIndicator = findViewById(R.id.dotsIndicator)

        viewPager.adapter = OnboardingAdapter()

        buttonSkip.setOnClickListener { viewModel.markOnboardingAsCompleted() }
        buttonNext.setOnClickListener { viewPager.currentItem++ }
        buttonGetStarted.setOnClickListener { viewModel.markOnboardingAsCompleted() }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                buttonGetStarted.isVisible = position + 1 == PAGE_COUNT
            }
        })
        dotsIndicator.attachTo(viewPager)

        viewModel.event
            .receiveAsFlow()
            .onEach(::onEventReceived)
            .launchIn(lifecycleScope)
    }

    private fun onEventReceived(event: OnboardingViewModel.Event){
        when(event){
            OnboardingViewModel.Event.MoveToNextStep -> {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }
    }

    companion object{
        private const val PAGE_COUNT = 4
    }
}