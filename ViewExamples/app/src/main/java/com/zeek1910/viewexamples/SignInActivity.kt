package com.zeek1910.viewexamples

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var loginButton: Button
    private lateinit var forgotPasswordButton: TextView
    private lateinit var signUpButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordButton = findViewById(R.id.forgotButton)
        signUpButton = findViewById(R.id.signUpButton)

        loginButton.setOnClickListener { onLoginClick() }
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra(KEY_EMAIL, email)
            startActivity(intent)
        }
    }

    private fun onLoginClick(){
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if (checkCredentialData(email, password)){
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Login failure", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkCredentialData(email: String, password: String): Boolean {
        return email == EMAIL && password == PASSWORD
    }

    companion object {
        const val EMAIL = "example@gmail.com"
        const val PASSWORD = "12345"

        const val KEY_EMAIL = "KEY_EMAIL"
    }
}