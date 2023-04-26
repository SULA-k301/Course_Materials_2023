package com.zeek1910.mvvmexample.ui.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeek1910.mvvmexample.R
import com.zeek1910.mvvmexample.models.Contact

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private val items: MutableList<Contact> = mutableListOf()

    fun setItems(items: List<Contact>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fistName = itemView.findViewById<TextView>(R.id.fistName)
        private val lastsName = itemView.findViewById<TextView>(R.id.lastName)
        private val phoneNumber = itemView.findViewById<TextView>(R.id.phoneNumber)

        fun bind(item: Contact) {
            fistName.text = item.fistName
            lastsName.text = item.lastName
            phoneNumber.text = item.phoneNumber
        }
    }
}