package com.zeek1910.navigationcomponentexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class AddItemFragment : Fragment() {

    private lateinit var addPhotoButton: Button
    private lateinit var addVideoButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPhotoButton = view.findViewById(R.id.addPhotoButton)
        addVideoButton = view.findViewById(R.id.addVideoButton)

        addPhotoButton.setOnClickListener {
            val bundle = bundleOf(
                "item" to ItemModel("Evhen", 30, 0.7f)
            )
            view.findNavController().navigate(R.id.action_addItemFragment_to_createPhotoFragment, bundle)
        }
        addVideoButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_addItemFragment_to_createVideoFragment)
        }
    }
}