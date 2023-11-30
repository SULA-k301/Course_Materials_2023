package com.zeek1910.examples.ui.fragments.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeek1910.examples.R
import com.zeek1910.examples.models.Tools
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

class ToolsAdapter : RecyclerView.Adapter<ToolsAdapter.ToolsViewHolder>() {

    private val items = Tools.values().toList()

    private val _event = Channel<Event>(capacity = Channel.CONFLATED)
    val event: ReceiveChannel<Event> get() = _event

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ToolsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_tool, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ToolsViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            _event.trySend(Event.OnItemClick(items[position]))
        }
        holder.itemView.setOnLongClickListener {
            _event.trySend(Event.OnItemLongClick(items[position]))
            true
        }
    }

    class ToolsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon = itemView.findViewById<ImageView>(R.id.icon)
        private val title = itemView.findViewById<TextView>(R.id.title)

        fun bind(tools: Tools) {
            icon.setImageResource(tools.iconId)
            title.setText(tools.titleId)
        }
    }

    sealed class Event{
        data class OnItemClick(val tools: Tools): Event()
        data class OnItemLongClick(val tools: Tools): Event()
    }
}