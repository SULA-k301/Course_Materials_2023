package com.example.intent_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textView = findViewById(R.id.textView)
        if (intent.hasExtra(MainActivity.TEXT_KEY)){
            val text = intent.getStringExtra(MainActivity.TEXT_KEY)
            textView.text = text
        }
    }
}