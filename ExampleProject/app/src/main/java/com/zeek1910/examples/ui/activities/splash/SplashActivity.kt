package com.zeek1910.examples.ui.activities.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zeek1910.examples.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.init()

        viewModel.eventChannel
            .receiveAsFlow()
            .onEach {
                when(it){
                    is SplashViewModel.Event.NavigateTo -> {
                        startActivity(it.intent)
                        finish()
                    }
                }
            }
            .launchIn(lifecycleScope)
    }
}