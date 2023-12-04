package com.zeek1910.examples.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.zeek1910.examples.R
import com.zeek1910.examples.ui.activities.MainActivity
import com.zeek1910.examples.ui.activities.signin.SignInActivity
import com.zeek1910.examples.utils.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var progressBar: ProgressBar

    private val viewModel by viewModels<SignUpViewModel> { SignUpViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        nameEditText = findViewById(R.id.name)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        signUpButton = findViewById(R.id.loginButton)
        progressBar = findViewById(R.id.progressBar)

        val email = intent.getStringExtra(SignInActivity.KEY_EMAIL)
        emailEditText.setText(email)

        signUpButton.setOnClickListener {
            viewModel.signUp(
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        viewModel.state
            .onEach(::onStateChanged)
            .launchIn(lifecycleScope)

        viewModel.action
            .receiveAsFlow()
            .onEach(::onActionReceived)
            .launchIn(lifecycleScope)
    }

    private fun onStateChanged(state: SignUpViewModel.State){
        progressBar.isVisible = state.isProgress
    }

    private fun onActionReceived(action: SignUpViewModel.Action){
        when(action){
            SignUpViewModel.Action.IncorrectDataError -> showToast("Name or email or password is empty")
            SignUpViewModel.Action.RegisterError -> showToast("Something went wrong. Try again later")
            SignUpViewModel.Action.RegisterSuccessfully -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}