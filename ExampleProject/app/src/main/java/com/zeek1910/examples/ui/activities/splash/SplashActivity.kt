package com.zeek1910.examples.ui.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zeek1910.examples.R
import com.zeek1910.examples.ui.activities.MainActivity
import com.zeek1910.examples.ui.activities.SignInActivity
import com.zeek1910.examples.ui.activities.onboarding.OnboardingActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels { SplashViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.init()

        viewModel.actionChannel
            .receiveAsFlow()
            .onEach {
                val intent = when (it) {
                    SplashViewModel.Action.NavigateToMain ->
                        Intent(this, MainActivity::class.java)

                    SplashViewModel.Action.NavigateToOnboarding ->
                        Intent(this, OnboardingActivity::class.java)

                    SplashViewModel.Action.NavigateToSignIn ->
                        Intent(this, SignInActivity::class.java)
                }
                startActivity(intent)
                finish()
            }
            .launchIn(lifecycleScope)
    }
}