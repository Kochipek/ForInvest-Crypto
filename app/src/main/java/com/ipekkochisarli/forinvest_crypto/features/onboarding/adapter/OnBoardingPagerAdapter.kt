package com.ipekkochisarli.forinvest_crypto.features.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipekkochisarli.forinvest_crypto.R
import com.ipekkochisarli.forinvest_crypto.databinding.ItemOnboardingBinding
import com.ipekkochisarli.forinvest_crypto.features.onboarding.uimodel.OnBoardingModel


class OnBoardingPagerAdapter(
    private val onBoardingPageData: List<OnBoardingModel>,
    private val onButtonClick: () -> Unit
) : RecyclerView.Adapter<OnBoardingPagerAdapter.OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val binding = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val page = onBoardingPageData[position]
        holder.bind(page)
    }

    override fun getItemCount(): Int = onBoardingPageData.size

    inner class OnboardingViewHolder(private val binding: ItemOnboardingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(page: OnBoardingModel) {
            binding.title.text = itemView.context.getString(page.titleResId)
            binding.description.text = itemView.context.getString(page.descriptionResId)
            binding.image.setImageResource(page.imageResId)

            if (adapterPosition == onBoardingPageData.size - 1) {
                binding.buttonNext.text = itemView.context.getString(R.string.get_started)
            } else {
                binding.buttonNext.text = itemView.context.getString(R.string.next)
            }

            binding.buttonNext.setOnClickListener {
                onButtonClick()
            }
        }
    }
}
