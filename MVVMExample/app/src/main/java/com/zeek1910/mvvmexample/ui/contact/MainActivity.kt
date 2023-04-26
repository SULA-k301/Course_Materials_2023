package com.zeek1910.mvvmexample.ui.contact

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zeek1910.mvvmexample.R
import com.zeek1910.mvvmexample.ui.contact.AddNewContactFragment
import com.zeek1910.mvvmexample.ui.contact.ContactAdapter
import com.zeek1910.mvvmexample.ui.contact.ContactViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity(), AddNewContactFragment.AddNewContactListener {

    private lateinit var contactRecyclerView: RecyclerView
    private lateinit var addContactButton: FloatingActionButton

    private val contactAdapter = ContactAdapter()

    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactRecyclerView = findViewById(R.id.contactRecyclerView)
        addContactButton = findViewById(R.id.addContactButton)
        addContactButton.setOnClickListener {
            AddNewContactFragment().show(supportFragmentManager, null)
        }

        setupRecyclerView()

        viewModel.contacts
            .onEach {
                contactAdapter.setItems(it)
            }.launchIn(lifecycleScope)

        viewModel.getAllContacts()
    }

    private fun setupRecyclerView() {
        contactRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactRecyclerView.adapter = contactAdapter
    }

    override fun onSaveContact(fistName: String, lastName: String, phone: String) {
        viewModel.addNewContact(fistName, lastName, phone)
    }
}