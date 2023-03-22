package com.zeek1910.recyclerviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SpecialistAdapter : RecyclerView.Adapter<SpecialistAdapter.SpecialistViewHolder>() {

    private val items: MutableList<Specialist> = mutableListOf()

    var clickListener: OnItemClickListener? = null
    var longClickListener: ((position: Int)-> Unit)? = null

    fun setItems(items: List<Specialist>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SpecialistViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_specialist, parent, false)
    )


    override fun onBindViewHolder(holder: SpecialistViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            clickListener?.onItemClick(items[position])
        }
        holder.itemView.setOnLongClickListener {
                longClickListener?.invoke(position)
            true
        }
    }

    override fun getItemCount() = items.size

    class SpecialistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val profession: TextView = itemView.findViewById(R.id.profession)
        private val experienceCount: TextView = itemView.findViewById(R.id.experienceCount)
        private val distance: TextView = itemView.findViewById(R.id.distance)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val reviewsCount: TextView = itemView.findViewById(R.id.reviewsCount)
        private val profileImage: ImageView = itemView.findViewById(R.id.profileImage)

        fun bind(item: Specialist) {
            name.text = item.name
            profession.text = item.profession
            experienceCount.text = item.experience.toString()
            distance.text = "${item.distance} km"
            price.text = "$${item.visitPrice}"
            reviewsCount.text = "${item.reviewsCount} Reviews"
            Glide.with(profileImage).load(item.imageUrl).into(profileImage)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(item: Specialist)
    }
}