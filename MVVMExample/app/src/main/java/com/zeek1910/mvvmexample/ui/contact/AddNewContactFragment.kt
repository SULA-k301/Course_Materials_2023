package com.zeek1910.mvvmexample.ui.contact

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zeek1910.mvvmexample.R

class AddNewContactFragment : DialogFragment() {

    private var listener: AddNewContactListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddNewContactListener) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.fragment_add_new_contact, null)

        val fistName = view.findViewById<EditText>(R.id.fistName)
        val lastName = view.findViewById<EditText>(R.id.lastName)
        val phoneNumber = view.findViewById<EditText>(R.id.phoneNumber)
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)

        cancelButton.setOnClickListener { dismiss() }
        saveButton.setOnClickListener {
            val fistNameValue = fistName.text.toString()
            val lastNameValue = lastName.text.toString()
            val phoneNumberValue = phoneNumber.text.toString()
            if (fistNameValue.isEmpty() || lastNameValue.isEmpty() || phoneNumberValue.isEmpty()){
                return@setOnClickListener
            }
            listener?.onSaveContact(fistNameValue, lastNameValue, phoneNumberValue)
            dismiss()
        }

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .create()
    }

    interface AddNewContactListener {
        fun onSaveContact(fistName: String, lastName: String, phone: String)
    }
}