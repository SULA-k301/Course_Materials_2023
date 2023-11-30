package com.zeek1910.examples.ui.fragments.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeek1910.examples.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

class ActivitiesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val toolsAdapter = ToolsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = toolsAdapter


        toolsAdapter.event
            .receiveAsFlow()
            .onEach {
                when (it) {
                    is ToolsAdapter.Event.OnItemClick -> {
                        Toast.makeText(
                            requireContext(),
                            "OnItemClick: ${it.tools}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is ToolsAdapter.Event.OnItemLongClick -> {
                        Toast.makeText(
                            requireContext(),
                            "OnItemLongClick: ${it.tools}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}