package com.zeek1910.examples.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeek1910.examples.R
import com.zeek1910.examples.ui.activities.MeditationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels { HomeViewModel.Factory }

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val meditationAdapter = MeditationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)

        setupRecyclerView()

        viewModel.state
            .onEach {
                progressBar.isVisible = it.isProgress
                meditationAdapter.setItems(it.data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.fetchData()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = meditationAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


}