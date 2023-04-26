package com.zeek1910.mvvmexample.ui.contact

import androidx.lifecycle.ViewModel
import com.zeek1910.mvvmexample.data.ContactRepository
import com.zeek1910.mvvmexample.models.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactViewModel : ViewModel() {

    private val contactRepository = ContactRepository()

    private val _contacts: MutableStateFlow<List<Contact>> = MutableStateFlow(emptyList())
    val contacts: StateFlow<List<Contact>> get() = _contacts.asStateFlow()

    fun addNewContact(fistName: String, lastName: String, phone: String) {
        contactRepository.addNewContact(Contact(fistName, lastName, phone))
        getAllContacts()
    }

    fun getAllContacts() {
        _contacts.tryEmit(contactRepository.getAllContacts())
    }
}