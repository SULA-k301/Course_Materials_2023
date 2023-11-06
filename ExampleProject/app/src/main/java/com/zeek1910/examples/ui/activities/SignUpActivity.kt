package com.zeek1910.examples.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.zeek1910.examples.R
import com.zeek1910.examples.data.AppSettings
import com.zeek1910.examples.models.User
import com.zeek1910.examples.App

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button

    private lateinit var appSettings: AppSettings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        appSettings = (application as App).appSettings

        nameEditText = findViewById(R.id.name)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        signUpButton = findViewById(R.id.loginButton)

        val email = intent.getStringExtra(SignInActivity.KEY_EMAIL)
        emailEditText.setText(email)

        signUpButton.setOnClickListener {
            val user = User(
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString(),
            )
            appSettings.saveUser(user)
            appSettings.setIsLoginSuccess()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}