package com.example.intent_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var goToSecondActivityButton: Button
    private lateinit var goToSecondActivityWithArgumentButton: Button
    private lateinit var getResultButton: Button
    private lateinit var texView: TextView

    private val text = "Hello"

    private val thirdActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK){
                val text = result.data?.getStringExtra(RESULT_TEXT_KEY)
                texView.text = text
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToSecondActivityButton = findViewById(R.id.buttonGoToSecondActivity)
        goToSecondActivityWithArgumentButton =
            findViewById(R.id.buttonGoToSecondActivityWithArgument)
        getResultButton = findViewById(R.id.buttonGetResult)
        texView = findViewById(R.id.textView)
        goToSecondActivityButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        goToSecondActivityWithArgumentButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(TEXT_KEY, text)
            startActivity(intent)
        }
        getResultButton.setOnClickListener {
            thirdActivityResult.launch(Intent(this, ThirdActivity::class.java))
        }
    }

    companion object {
        const val TEXT_KEY = "TEXT_KEY"
        const val RESULT_TEXT_KEY = "RESULT_TEXT_KEY"
    }
}