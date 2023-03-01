package com.example.intent_example

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity: AppCompatActivity() {

    private lateinit var editText:EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.buttonFinish)

        button.setOnClickListener {
            val text = editText.text.toString()
            val intent = Intent()
            intent.putExtra(MainActivity.RESULT_TEXT_KEY, text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}