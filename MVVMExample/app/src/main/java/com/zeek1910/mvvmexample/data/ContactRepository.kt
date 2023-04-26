package com.zeek1910.mvvmexample.data

import com.zeek1910.mvvmexample.models.Contact

class ContactRepository {
    private val data = mutableListOf<Contact>()

    fun addNewContact(contact: Contact){
        data.add(contact)
    }

    fun getAllContacts(): List<Contact> = data.toList()
}