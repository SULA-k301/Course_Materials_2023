package com.zeek1910.examples.ui.activities.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeek1910.examples.R
import com.zeek1910.examples.models.OnboardingItem

class OnboardingAdapter : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    private val items = listOf(
        OnboardingItem(
            R.string.onboarding_page_1_title,
            R.drawable.onboarding_image_page_1,
            R.string.onboarding_page_sub_title
        ),
        OnboardingItem(
            R.string.onboarding_page_2_title,
            R.drawable.onboarding_image_page_2,
            R.string.onboarding_page_sub_title
        ),
        OnboardingItem(
            R.string.onboarding_page_3_title,
            R.drawable.onboarding_image_page_3,
            R.string.onboarding_page_sub_title
        ),
        OnboardingItem(
            R.string.onboarding_page_4_title,
            R.drawable.onboarding_image_page_4,
            R.string.onboarding_page_sub_title
        )
    )

    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val subTitle = itemView.findViewById<TextView>(R.id.subTitle)
        private val image = itemView.findViewById<ImageView>(R.id.image)

        fun setItem(onboardingItem: OnboardingItem) {
            title.text = itemView.context.getString(onboardingItem.titleId)
            subTitle.text = itemView.context.getString(onboardingItem.subTitleId)
            image.setImageResource(onboardingItem.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.setItem(items[position])
    }
}