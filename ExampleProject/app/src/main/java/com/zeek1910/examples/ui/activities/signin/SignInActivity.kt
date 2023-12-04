package com.zeek1910.examples.ui.activities.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.zeek1910.examples.R
import com.zeek1910.examples.ui.activities.MainActivity
import com.zeek1910.examples.ui.signup.SignUpActivity
import com.zeek1910.examples.utils.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

class SignInActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var loginButton: Button
    private lateinit var forgotPasswordButton: TextView
    private lateinit var signUpButton: TextView

    private lateinit var progressBar: ProgressBar

    private val viewModel by viewModels<SignInViewModel> { SignInViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordButton = findViewById(R.id.forgotButton)
        signUpButton = findViewById(R.id.signUpButton)
        progressBar = findViewById(R.id.progressBar)

        loginButton.setOnClickListener {
            viewModel.signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra(KEY_EMAIL, email)
            startActivity(intent)
        }

        emailEditText.addTextChangedListener {
            viewModel.onEmailOrPasswordChange(it.toString(), passwordEditText.text.toString())
        }
        passwordEditText.addTextChangedListener {
            viewModel.onEmailOrPasswordChange(emailEditText.text.toString(), it.toString())
        }

        viewModel.state
            .onEach {
                loginButton.isEnabled = it.isSignInButtonEnable
                progressBar.isVisible = it.isProgress
            }
            .launchIn(lifecycleScope)

        viewModel.action
            .receiveAsFlow()
            .onEach {
                when (it) {
                    SignInViewModel.Action.IncorrectCredentials -> showToast("Email or password are incorrect")
                    SignInViewModel.Action.LoginSuccessfully -> {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }

                    SignInViewModel.Action.EmptyData -> showToast("Email or password is empty")
                }
            }
            .launchIn(lifecycleScope)
    }

    companion object {
        const val KEY_EMAIL = "KEY_EMAIL"
    }
}