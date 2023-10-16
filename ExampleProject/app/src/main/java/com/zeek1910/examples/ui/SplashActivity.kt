package com.zeek1910.examples.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zeek1910.examples.R
import com.zeek1910.examples.data.AppSettings
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