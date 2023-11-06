package com.zeek1910.examples.ui.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeek1910.examples.R
import com.zeek1910.examples.models.MeditationItem


class MeditationAdapter : RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder>() {

    private val items = mutableListOf<MeditationItem>()

    fun setItems(items: List<MeditationItem>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class MeditationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val subTitle = itemView.findViewById<TextView>(R.id.subTitle)
        private val buttonWatchNow = itemView.findViewById<Button>(R.id.buttonWatchNow)
        private val image = itemView.findViewById<ImageView>(R.id.image)

        fun setItem(meditationItem: MeditationItem){
            title.text = meditationItem.title
            subTitle.text = meditationItem.subTitle
            buttonWatchNow.setOnClickListener {
                //TODO
            }
            image.setImageResource(meditationItem.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        return MeditationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_meditation, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        holder.setItem(items[position])
    }
}