package com.zeek1910.examples.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.zeek1910.examples.R
import com.zeek1910.examples.ui.activities.signin.SignInActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

class ProfileFragment : Fragment() {

    private lateinit var buttonLogout: Button
    private lateinit var profileImage: ImageView
    private lateinit var profileName: TextView
    private lateinit var progressBar: ProgressBar

    private val viewModel: ProfileViewModel by viewModels { ProfileViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        profileName = view.findViewById(R.id.profileName)
        profileImage = view.findViewById(R.id.profileImage)
        buttonLogout = view.findViewById(R.id.buttonLogout)
        buttonLogout.setOnClickListener { viewModel.onLogoutClick() }

        viewModel.event
            .receiveAsFlow()
            .onEach(::onEventReceived)
            .launchIn(lifecycleScope)

        viewModel.state
            .onEach(::onStateChanged)
            .launchIn(lifecycleScope)
    }

    private fun onStateChanged(state: ProfileViewModel.State){
        Glide.with(this)
            .load(state.profileImageUrl)
            .into(profileImage)
        profileName.text = state.profileName
        progressBar.isVisible = state.isProgress
    }

    private fun onEventReceived(event: ProfileViewModel.Event) {
        when (event) {
            ProfileViewModel.Event.GoToSignInScreen -> {
                startActivity(Intent(requireContext(), SignInActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}