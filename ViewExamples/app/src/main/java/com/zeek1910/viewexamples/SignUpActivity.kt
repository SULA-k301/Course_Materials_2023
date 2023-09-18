package com.zeek1910.viewexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class SignUpActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailEditText = findViewById(R.id.email)

        val email = intent.getStringExtra(SignInActivity.KEY_EMAIL)
        emailEditText.setText(email)
    }
}